let host = "http://localhost:8080/rest";
const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http) {
    $scope.form = {};
    $scope.items = [];

    // Reset the form
    $scope.reset = function () {
        $scope.form = {};
    };

    // Load all users
    $scope.load_all = function () {
        var url = `${host}/user`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    };

    // Edit user
    $scope.edit = function (id) {
        if (typeof id !== 'number' && typeof id !== 'string') {
            console.error("Invalid ID:", id);
            return;
        }

        var url = `${host}/user/${id}`;
        $http.get(url).then(resp => {
            $scope.form = resp.data;
            console.log("Edit Success", resp.data);
        }).catch(error => {
            console.log("Error", error);
        });
    };

    // Create a new user
    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/user`;
        $http.post(url, item).then(resp => {
            $scope.items.push(resp.data); // Add the new user to the list
            $scope.reset();
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    };

    // Update an existing user
    $scope.update = function () {
        if (typeof $scope.form.userId !== 'number' && typeof $scope.form.userId !== 'string') {
            console.error("Invalid User ID:", $scope.form.userId);
            return;
        }

        var item = angular.copy($scope.form);
        var url = `${host}/user/${$scope.form.userId}`;
        $http.put(url, item).then(resp => {
            var index = $scope.items.findIndex(i => i.userId == $scope.form.userId);
            if (index !== -1) {
                $scope.items[index] = resp.data;
            }
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    };

    // Delete a user
    $scope.delete = function (id) {
        if (typeof id !== 'number' && typeof id !== 'string') {
            console.error("Invalid ID:", id);
            return;
        }

        var url = `${host}/user/${id}`;
        $http.delete(url).then(resp => {
            var index = $scope.items.findIndex(i => i.userId == id);
            if (index !== -1) {
                $scope.items.splice(index, 1);
                $scope.reset();
                console.log("Delete Success", resp);
            }
        }).catch(error => {
            console.log("Error", error);
        });
    };

    // Optional: Search users
    $scope.search = function () {
        var query = $scope.searchQuery;
        var url = `${host}/user/search?query=${query}`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Search Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    };

    // Load all users and reset the form on controller initialization
    $scope.load_all();
    $scope.reset();
});
