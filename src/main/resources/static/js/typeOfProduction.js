var app = angular.module("demo", []);

app.controller("AppCtrl", function($scope, $http){

    /******************************TypeOfProductionCRUD*************************/
    $scope.typeOfProduction = [];
    $http.get('/api/type/production').then(function (response){
        $scope.typeOfProduction = response.data;
        console.log(response);
    });

    this.del_type_of_production = function del(id) {
        $http.get('/api/type/production/del?id=' + id).then(function (response) {
            window.location.reload();
        });
    };

    this.insert_type_of_production = function add() {
        var id = document.getElementById("typeOfProductionID").value;
        var name = document.getElementById("typeOfProductionName").value;

        var isValid=true;
        var errorMessage='Помилка: неправильні вхідні дані!\n';
        var regexName=/^([А-ЯІ][а-яі]+)|([А-ЯІ][а-яі]+\s[а-яі]+)|([А-ЯІ][а-яі]+\s[а-яі]+\s[а-яі]+)$/ ;
        if(!regexName.test(name.toString())){
            errorMessage=errorMessage+'-невірний формат Імені;\n';
            errorMessage=errorMessage+'Потрібний формат Перша буква з великої, далі малі, кирилицею;';
            isValid=false;
        }
        if (isValid) {
            $http.get('/api/type/production/add?id='+id+'&nameOfType='+name).then(function (response){
                window.location.reload();
            });
        }
        else window.alert(errorMessage);
    };

    var idTypeOfProduction;
    this.start_update_type_of_production = function updt(id) {
        idTypeOfProduction=id;
    };

    this.update_type_of_production = function upd() {
        var name = document.getElementById("typeOfProductionNameUPD").value;

        var isValid=true;
        var errorMessage='Помилка: неправильні вхідні дані!\n';
        var regexName=/^([А-ЯІ][а-яі]+)|([А-ЯІ][а-яі]+\s[а-яі]+)|([А-ЯІ][а-яі]+\s[а-яі]+\s[а-яі]+)$/ ;
        if(!regexName.test(name.toString())){
            errorMessage=errorMessage+'-невірний формат Імені;\n';
            errorMessage=errorMessage+'Потрібний формат Перша буква з великої, далі малі, кирилицею;';
            isValid=false;
        }
        if (isValid) {
            $http.get('/api/type/production/upd?id='+idTypeOfProduction+'&nameOfType='+name).then(function (response){
                window.location.reload();
            });
        }
        else window.alert(errorMessage);


    };
});