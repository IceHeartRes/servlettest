<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Part filter</title>
</head>
<body>
<div id="errorMessage" style='color: red'></div>
<table>
    <tr>
        <td style='width: 150px;'>Filter</td>
    </tr>
    <tr>
        <td>PN</td>
        <td><input id="partNumber" type="text"/></td>
    </tr>
    <tr>
        <td>Part Name</td>
        <td><input id="partName" type="text"/></td>
    </tr>
    <tr>
        <td>Vendor</td>
        <td><input id="vendor" type="text"/></td>
    </tr>
    <tr>
        <td>Qty</td>
        <td><input id="qty" type="number"/></td>
    </tr>
    <tr>
        <td>Shipped</td>
        <td>after<input id="shippedAfter" type="text"/> before<input id="shippedBefore" type="text"/></td>
        <%--можно type поставить в date, но не удается найти простого способа осуществить форматирование даты--%>
    </tr>
    <tr>
        <td>Receive</td>
        <td>after<input id="receiveAfter" type="text"/> before<input id="receiveBefore" type="text"/></td>
    </tr>
</table>

<br>
<br>


<div>
    <div>
        <input id="buttonSend" type="button" value="Отправить AJAX запрос" onclick="sendRequest()"/>
    </div>
    <div id="tablePrint"></div>
</div>

</body>
</html>

<script language="javascript" type="text/javascript">

    var sortDirection = ['asc', 'desc'];
    var currentSortColumn = "";
    var currentSortDirectionIndex = 0;

    function makePartsTable(parts) {
        var myTable = "<table>";
        myTable += makeHeaderRow();
        for (var i = 0; i < parts.length; i++) {
            myTable += addRow(parts[i]);
        }
        myTable += "</table>";
        document.getElementById('tablePrint').innerHTML = myTable;
    }

    function makeHeaderRow() {
        return "<tr>" +
            makeHeaderColumn('Part Number', 'part_number') +
            makeHeaderColumn('Part Name', 'part_name') +
            makeHeaderColumn('Vendor', 'vendor') +
            makeHeaderColumn('Qty', 'qty') +
            makeHeaderColumn('Shipped', 'shipped') +
            makeHeaderColumn('Receive', 'receive') +
            "</tr>";
    }

    function addRow(parts) {
        return "<tr>" +
            addColumn(parts.part_number) +
            addColumn(parts.part_name) +
            addColumn(parts.vendor) +
            addColumn(parts.qty) +
            addColumn(parts.shipped) +
            addColumn(parts.receive) +
            "</tr>";
    }

    function addColumn(content) {
        return "<td style='text-align: right;'>" + content + " </td>";
    }

    function makeHeaderColumn(headerText, sortColumn) {
        return "<td style='width: 150px; background: lightgray; text-align: center;'> " + makeHeaderLink(headerText, sortColumn) + "</td>";
    }

    function makeHeaderLink(headerText, sortColumn) {
        return '<a href="#" onclick="sendRequest(\'' + sortColumn + '\')">' + headerText + "</a>"
    }


    function sendRequest(sortColumn) {
        var errorMessage = document.getElementById('errorMessage');
        errorMessage.innerHTML = '';

        if (currentSortColumn !== sortColumn) {
            currentSortColumn = sortColumn;
            currentSortDirectionIndex = 0;
        }

        var filterRequestDto = {
            part_number: document.getElementById('partNumber').value,
            part_name: document.getElementById('partName').value,
            vendor: document.getElementById('vendor').value,
            qty: document.getElementById('qty').value,
            shipped_after: document.getElementById('shippedAfter').value,
            shipped_before: document.getElementById('shippedBefore').value,
            receive_after: document.getElementById('receiveAfter').value,
            receive_before: document.getElementById('receiveBefore').value,
            sort_column: currentSortColumn,
            sort_direction: sortDirection[currentSortDirectionIndex++ & 1]
        };

        fetch('filter', {
            method: 'POST',
            body: JSON.stringify(filterRequestDto)
        })
            .then(function (response) {
                if (response.status !== 200) {
                    return Promise.reject("Error while processed post request");
                }
                return response.json();
            })
            .then(function (json) {
                responseDto = JSON.parse(JSON.stringify(json));
                makePartsTable(responseDto.parts);
            })
            .catch(function (err) {
                errorMessage.innerHTML = err + " <br> <br>"
            })
    }
</script>