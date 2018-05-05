var app = angular.module("demo", []);

app.controller("AppCtrl", function($scope, $http){

    /******************************BuyerCRUD*************************/
    $scope.buyers = [];
    $http.get('/api/buyers').then(function (response) {
        $scope.buyers = response.data;
        console.log(response);
    });

    this.del_buyer = function del(id) {
        $http.get('/api/buyers/del?id=' + id).then(function (response) {
            window.location.reload();
        });
    };

    this.insert_buyer = function add() {
        var id = document.getElementById("buyerId").value;
        var pib = document.getElementById("buyerPIB").value;
        var age = document.getElementById("buyerAge").value;

        $http.get('/api/buyers/add?id='+id+'&PIB='+pib+'&age='+age).then(function (response){
            window.location.reload();
        });

    };

    var idBuyer;
    this.start_update_buyer = function updt(id, PIB, age) {
        idBuyer=id;
        document.getElementById("buyerPIBUPD").value = PIB;
        document.getElementById("buyerAgeUPD").value = age;
    };

    this.update_buyer = function upd() {
        var pib = document.getElementById("buyerPIBUPD").value;
        var age = document.getElementById("buyerAgeUPD").value;

        $http.get('/api/buyers/upd?id='+idBuyer+'&PIB='+pib+'&age='+age).then(function (response){
            window.location.reload();
        });
    };
});