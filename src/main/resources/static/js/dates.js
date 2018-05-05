var app = angular.module("demo", []);

app.controller("AppCtrl", function($scope, $http){

    /******************************DatesCRUD*************************/
    $scope.dates = [];
    $http.get('/api/dates').then(function (response){
        $scope.dates = response.data;
        console.log(response);
    });

    this.del_dates = function del(id) {
        $http.get('/api/dates/del?id=' + id).then(function (response) {
            window.location.reload();
        });
    };

    this.start_insert_dates = function add() {

        $http.get('/api/order_status').then(function (response){
            var orderStatus = response.data;
            var selector = document.getElementById("orderStatusID");
            $(selector).empty();
            for (var i = 0; i < orderStatus.length; i++) {
                var option = document.createElement("option");
                option.text = orderStatus[i].nameOfStatus;
                option.value = orderStatus[i].id;
                console.log(option);
                selector.add(option);
            }
        });
    };

    this.insert_date = function add() {
        var id = document.getElementById("datesId").value;
        var dateOfOrdering = document.getElementById("dateOfOrdering").value;
        var dateOfReceiving = document.getElementById("dateOfReceiving").value;
        var indexOfOrderStatus = document.getElementById("orderStatusID").selectedIndex;
        var orderStatus = document.getElementById("orderStatusID").options[indexOfOrderStatus].value

        $http.get('/api/dates/add?id='+id+'&dateOfOrdering='+dateOfOrdering+'&dateOfReceiving='
            +dateOfReceiving+'&orderStatus_id='+orderStatus).then(function (response){
            setTimeout(function(){
                window.location.reload();
            });
        });
    };

    var idDates;
    this.start_update_dates = function updt(id, ordering, receiving) {
        idDates = id;
        document.getElementById("dateOfOrderingUPD").value = ordering;
        document.getElementById("dateOfReceivingUPD").value = receiving;

        $http.get('/api/order_status').then(function (response){
            var orderStatus = response.data;
            var selector = document.getElementById("orderStatusIDUPD");
            $(selector).empty();
            for (var i = 0; i < orderStatus.length; i++) {
                var option = document.createElement("option");
                option.text = orderStatus[i].nameOfStatus;
                option.value = orderStatus[i].id;
                console.log(option);
                selector.add(option);
            }
        });
    };

    this.update_date = function upd() {
        var dateOfOrdering = document.getElementById("dateOfOrderingUPD").value;
        var dateOfReceiving = document.getElementById("dateOfReceivingUPD").value;
        var indexOfOrderStatus = document.getElementById("orderStatusIDUPD").selectedIndex;
        var orderStatus = document.getElementById("orderStatusIDUPD").options[indexOfOrderStatus].value

        $http.get('/api/dates/upd?id='+idDates+'&dateOfOrdering='+dateOfOrdering+'&dateOfReceiving='
            +dateOfReceiving+'&orderStatus_id='+orderStatus).then(function (response){
            setTimeout(function(){
                window.location.reload();
            });
        });
    };
});