<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./template/header_admin.jsp"/>
<c:import url="./template/content_left.jsp"/>

<div id="content-right">
                <div class="path-admin">DASHBOARD</b></div>
                <div class="content-main">
                    <div id="content-main-dashboard">
                        <div id="dashboard-1">
                            <div id="dashboard-1-container">
                                <div class="dashboard-item">
                                    <div class="dashboard-item-title">Weekly Sales</div>
                                    <div class="dashboard-item-content">$${totalWeek}</div>
                                </div>
                                <div class="dashboard-item">
                                    <div class="dashboard-item-title">Total Orders</div>
                                    <div class="dashboard-item-content">$${totalOrder}</div>
                                </div>
                                <div class="dashboard-item">
                                    <div class="dashboard-item-title">Total Customers</div>
                                    <div class="dashboard-item-content">${totalCus}</div>
                                </div>
                                <div class="dashboard-item">
                                    <div class="dashboard-item-title">Total Guest</div>
                                    <div class="dashboard-item-content">${allguest}</div>
                                </div>
                            </div>
                        </div>
                        <div id="dashboard-2">
                            <div id="chart" style="text-align: center;">
                                <div id="chart1">
                                    <h3>Statistic Orders (Month)</h3>
                                    <canvas id="myChart1" style="width: 100%;"></canvas>
                                </div>
                                <div id="chart2">
                                    <canvas id="myChart2" style="width: 80%;"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
<c:import url="./template/footer.jsp"/>
<script>
    function OrdersChart(){
        var xValues = [1,2,3,4,5,6,7,8,9,10,11,12];
    
        new Chart("myChart1", {
        type: "line",
        data: {
            labels: xValues,
            datasets: [{ 
            data: ${total},
            borderColor: "sienna",
            fill: true
            }]
        },
        options: {
            legend: {display: false}
        }
        });
    }

    function CustomersChart(){
        var xValues = ["Total", "New customer"];
        var yValues = [${totalCus}, ${newCus},300];
        var barColors = ["green", "red"];

        new Chart("myChart2", {
        type: "bar",
        data: {
            labels: xValues,
            datasets: [{
            backgroundColor: barColors,
            data: yValues
            }]
        },
        options: {
            legend: {display: false},
            title: {
            display: true,
            text: "New Customers (30 daily Avg)"
            }
        }
        });
    }
    
    OrdersChart();
    CustomersChart();
    </script>