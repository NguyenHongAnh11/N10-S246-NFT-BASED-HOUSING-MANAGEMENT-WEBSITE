const app = angular.module("Property-Favorite-app", []);
app.controller("Property-Favorite-ctrl", function ($scope, $http) {

    $scope.favorite = {
        items: [],
        message: "",  // Biến để lưu thông báo

        // Thêm property vào mục yêu thích
        add(propertyId) {
            console.log('Received propertyId:', propertyId);

            // Kiểm tra propertyId hợp lệ
            if (isNaN(propertyId) || propertyId === undefined || propertyId === null) {
                console.error('Invalid property ID');
                return;
            }

            propertyId = parseInt(propertyId, 10); // Chuyển thành số nguyên

            // Kiểm tra sản phẩm đã có trong danh sách yêu thích chưa
            var item = this.items.find(item => item.propertyId == propertyId);
            if (item) {
                // Nếu sản phẩm đã có, hiển thị thông báo nhưng không tăng qty
                $scope.favorite.message = "Bạn đã yêu thích sản phẩm này!";
                $('#favoriteModal').modal('show');  // Mở Modal
            } else {
                // Nếu sản phẩm chưa có, thêm sản phẩm với qty = 1
                $http.get(`/rest/properties/${propertyId}`).then(resp => {
                    resp.data.qty = 1; // Đặt qty ban đầu là 1
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                    $scope.favorite.message = "Đã thêm sản phẩm vào mục yêu thích!";
                    $('#favoriteModal').modal('show');  // Mở Modal
                }).catch(error => {
                    console.error('Error fetching property:', error);
                });
            }
        },

        // Tính tổng số lượng các property trong yêu thích
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },

        // Xóa property khỏi mục yêu thích
        remove(propertyId) {
            var index = this.items.findIndex(item => item.propertyId == propertyId);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },

        // Xóa sạch các property trong yêu thích
        clear() {
            this.items = []; // Xóa tất cả các mục trong danh sách yêu thích
            this.saveToLocalStorage();
            // Điều hướng về trang layout/home
            window.location.href = '/layout/home'; // Thay đổi '/layout/home' thành đường dẫn chính xác đến trang layout/home của bạn
        },

        // Lưu danh sách yêu thích vào local storage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("favorite", json);
        },

        // Đọc danh sách yêu thích từ local storage
        loadFromLocalStorage() {
            var json = localStorage.getItem("favorite");
            this.items = json ? JSON.parse(json) : [];
        }
    }

    // Khởi tạo: tải danh sách yêu thích từ local storage
    $scope.favorite.loadFromLocalStorage();
});
