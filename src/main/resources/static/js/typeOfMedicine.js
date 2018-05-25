var app = angular.module("demo", []);

app.controller("AppCtrl", function($scope, $http){
    /******************************TypeOfMedicineCRUD*************************/
    $scope.typeOfMedicine = [];
    $http.get('/api/type/medicine').then(function (response){
        $scope.typeOfMedicine = response.data;
        console.log(response);
    });

    this.del_type_of_medicine = function del(id) {
        $http.get('/api/type/medicine/del?id=' + id).then(function (response) {
            window.location.reload();
        });
    };

    this.start_insert_type_of_medicine = function add() {

        $http.get('/api/general_types').then(function (response){
            var types = response.data;
            var selector = document.getElementById("generalType");
            $(selector).empty();
            for (var i = 0; i < types.length; i++) {
                var option = document.createElement("option");
                option.text = types[i].nameOfGeneralType;
                option.value = types[i].id;
                console.log(option);
                selector.add(option);
            }

                $http.get('/api/type/production').then(function (response){
                    var types = response.data;
                    var selector = document.getElementById("productionType");
                    $(selector).empty();
                    for (var i = 0; i < types.length; i++) {
                        var option = document.createElement("option");
                        option.text = types[i].nameOfType;
                        option.value = types[i].id;
                        console.log(option);
                        selector.add(option);
                    }
                });
            });
    };

    this.insert_type_of_medicine = function add() {
        var id = document.getElementById("id").value;
        var typeName = document.getElementById("typeName").value;
        var indexOfGeneralType = document.getElementById("generalType").selectedIndex;
        var general_type_id = document.getElementById("generalType").options[indexOfGeneralType].value;
        var indexOfProductionType = document.getElementById("productionType").selectedIndex;
        var production_type_id = document.getElementById("productionType").options[indexOfProductionType].value;

        var isValid=true;
        var errorMessage='Помилка: неправильні вхідні дані!\n';
        var regexName=/^([А-ЯІ][а-яі]+)|([А-ЯІ][а-яі]+\s[а-яі]+)|([А-ЯІ][а-яі]+\s[а-яі]+\s[а-яі]+)$/ ;
        if(!regexName.test(typeName.toString())){
            errorMessage=errorMessage+'-невірний формат Імені;\n';
            errorMessage=errorMessage+'Потрібний формат Перша буква з великої, далі малі, кирилицею;';
            isValid=false;
        }
        if (isValid) {
            $http.get('/api/type/medicine/add?id='+id+'&nameOfType='+typeName+'&gen_type_id='
                +general_type_id+'&production_id='+production_type_id).then(function (response){
                window.location.reload();
            });
        }
        else window.alert(errorMessage);
    };

    var idTypeOfMedicine;
    this.start_update_type_of_medicine = function updt(id, nameOfType) {
        idTypeOfMedicine = id;
        document.getElementById("typeNameUPD").value = nameOfType;

        $http.get('/api/general_types').then(function (response){
            var types = response.data;
            var selector = document.getElementById("generalTypeUPD");
            $(selector).empty();
            for (var i = 0; i < types.length; i++) {
                var option = document.createElement("option");
                option.text = types[i].nameOfGeneralType;
                option.value = types[i].id;
                console.log(option);
                selector.add(option);
            }

                $http.get('/api/type/production').then(function (response){
                    var types = response.data;
                    var selector = document.getElementById("productionTypeUPD");
                    $(selector).empty();
                    for (var i = 0; i < types.length; i++) {
                        var option = document.createElement("option");
                        option.text = types[i].nameOfType;
                        option.value = types[i].id;
                        console.log(option);
                        selector.add(option);
                    }
                });
            });
    };

    this.update_type_of_medicine = function upd() {
        var typeName = document.getElementById("typeNameUPD").value;
        var indexOfGeneralType = document.getElementById("generalTypeUPD").selectedIndex;
        var general_type_id = document.getElementById("generalTypeUPD").options[indexOfGeneralType].value;
        var indexOfProductionType = document.getElementById("productionTypeUPD").selectedIndex;
        var production_type_id = document.getElementById("productionTypeUPD").options[indexOfProductionType].value;

        var isValid=true;
        var errorMessage='Помилка: неправильні вхідні дані!\n';
        var regexName=/^([А-ЯІ][а-яі]+)|([А-ЯІ][а-яі]+\s[а-яі]+)|([А-ЯІ][а-яі]+\s[а-яі]+\s[а-яі]+)$/ ;
        if(!regexName.test(typeName.toString())){
            errorMessage=errorMessage+'-невірний формат Імені;\n';
            errorMessage=errorMessage+'Потрібний формат Перша буква з великої, далі малі, кирилицею;';
            isValid=false;
        }
        if (isValid) {
            $http.get('/api/type/medicine/upd?id='+idTypeOfMedicine+'&nameOfType='+typeName
                +'&gen_type_id='+general_type_id+'&production_id='+production_type_id)
                .then(function (response){
                    window.location.reload();
                });
        }
        else window.alert(errorMessage);
    };


});