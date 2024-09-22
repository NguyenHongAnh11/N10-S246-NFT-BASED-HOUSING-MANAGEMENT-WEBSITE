let host = "http://localhost:8080/rest";
const app = angular.module("app", []);
app.controller("propertyCtrl", function ($scope, $http) {
    $scope.form = {};
    $scope.items = [];

    // Reset the form
    $scope.reset = function () {
        $scope.form = {};
    };

    // Load all properties
    $scope.load_all = function () {
        var url = `${host}/properties`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Load all Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    };

    // Edit a property
    $scope.edit = function (id) {
        if (typeof id !== 'number' && typeof id !== 'string') {
            console.error("Invalid ID:", id);
            return;
        }

        var url = `${host}/properties/${id}`;
        $http.get(url).then(resp => {
            $scope.form = resp.data;
            console.log("Edit Success", resp.data);
        }).catch(error => {
            console.log("Error", error);
        });
    };

    // Create a new property
    $scope.create = function () {
        var formData = new FormData();
        formData.append("address", $scope.form.address);
        formData.append("type", $scope.form.type);
        formData.append("price", $scope.form.price);
        formData.append("description", $scope.form.description);
        formData.append("status", $scope.form.status);
        formData.append("images", document.getElementById("images").files[0]);

        var url = `${host}/properties`;
        $http.post(url, formData, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            console.log("Create Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    };

    // Update an existing property
    $scope.update = function () {
        if (typeof $scope.form.propertyId !== 'number' && typeof $scope.form.propertyId !== 'string') {
            console.error("Invalid Property ID:", $scope.form.propertyId);
            return;
        }

        var item = angular.copy($scope.form);
        var url = `${host}/properties/${$scope.form.propertyId}`;
        $http.put(url, item).then(resp => {
            var index = $scope.items.findIndex(i => i.propertyId == $scope.form.propertyId);
            if (index !== -1) {
                $scope.items[index] = resp.data;
            }
            console.log("Update Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    };

    // Delete a property
    $scope.delete = function (id) {
        if (typeof id !== 'number' && typeof id !== 'string') {
            console.error("Invalid ID:", id);
            return;
        }

        var url = `${host}/properties/${id}`;
        $http.delete(url).then(resp => {
            var index = $scope.items.findIndex(i => i.propertyId == id);
            if (index !== -1) {
                $scope.items.splice(index, 1);
                $scope.reset();
                console.log("Delete Success", resp);
            }
        }).catch(error => {
            console.log("Error", error);
        });
    };

    // Optional: Search properties
    $scope.search = function () {
        var query = $scope.searchQuery;
        var url = `${host}/properties/search?query=${query}`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Search Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    };

    // Load all properties and reset the form on controller initialization
    $scope.load_all();
    $scope.reset();
});
