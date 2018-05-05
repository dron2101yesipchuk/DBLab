var app = angular.module("demo", []);

app.controller("AppCtrl", function($scope, $http){

    /******************************TypeOfUsingCRUD*************************/
    $scope.typeOfUsing = [];
    $http.get('/api/type/using').then(function (response){
        $scope.typeOfUsing = response.data;
        console.log(response);
    });

    this.del_type_of_using = function del(id) {
        $http.get('/api/type/using/del?id=' + id).then(function (response) {
            window.location.reload();
        });
    };

    this.insert_type_of_using = function add() {
        var id = document.getElementById("typeOfUsingID").value;
        var name = document.getElementById("typeOfUsingName").value;

        $http.get('/api/type/using/add?id='+id+'&nameOfType='+name).then(function (response){
            window.location.reload();
        });
    };

    var idTypeOfUsing;
    this.start_update_type_of_using = function updt(id) {
        idTypeOfUsing=id;
    };

    this.update_type_of_using = function upd() {
        var name = document.getElementById("typeOfUsingNameUPD").value;

        $http.get('/api/type/using/upd?id='+idTypeOfUsing+'&nameOfType='+name).then(function (response){
            window.location.reload();
        });
    };
});