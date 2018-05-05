var app = angular.module("demo", []);

app.controller("AppCtrl", function($scope, $http){

    /******************************IngredientsCRUD*************************/
    $scope.ingredients = [];
    $http.get('/api/ingredients').then(function (response){
        $scope.ingredients = response.data;
        console.log(response);
    });

    this.del_ingredients = function del(id) {
        $http.get('/api/ingredients/del?id=' + id).then(function (response) {
            window.location.reload();
        });
    };

    this.insert_ingredients = function add() {
        var id = document.getElementById("ingredientsId").value;
        var name = document.getElementById("ingredientsName").value;
        var criticalRate = document.getElementById("ingredientsCriticalRate").value;
        var amount = document.getElementById("ingredientsAmount").value;
        var price = document.getElementById("ingredientsPrice").value;

        $http.get('/api/ingredients/add?id='+id+'&name='+name+'&criticalRate='+criticalRate+
        '&amount='+amount+'&price='+price).then(function (response){
            window.location.reload();
        });
    };

    var idIngredients;
    this.start_update_ingredients = function updt(id) {
        idIngredients=id;
    };

    this.update_ingredients = function upd() {
        var name = document.getElementById("ingredientsNameUPD").value;
        var criticalRate = document.getElementById("ingredientsCriticalRateUPD").value;
        var amount = document.getElementById("ingredientsAmountUPD").value;
        var price = document.getElementById("ingredientsPriceUPD").value;

        $http.get('/api/ingredients/upd?id='+idIngredients+'&name='+name+'&criticalRate='+criticalRate+
            '&amount='+amount+'&price='+price).then(function (response){
            window.location.reload();
        });
    };
});