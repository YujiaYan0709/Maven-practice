'use strict';

userApp.controller('UserListCtrl', ['$scope', '$rootScope', '$filter', '$location', 'Constants',
    'rootService', 'pager', 'udcModal', 'UserService', function ($scope, $rootScope, $filter, $location, Constants,
                                                          rootService, pager, udcModal, UserService) {
        var gotoPage = function (pageNo) {
            $scope.pager.setCurPageNo(pageNo);
            searchUsers();
        };

        $scope.pager = pager.init('UserListCtrl', gotoPage);
        var historyQ = $scope.pager.getQ();

        $scope.q = {
            userName: historyQ.userName || ""
        };

        $scope.vm = {
            userList: []
        };

        $scope.search = function () {
            $scope.pager.setCurPageNo(1);
            searchUsers();
        };

        $scope.initPopUp = function () {
            udcModal.show({
                templateUrl: "./user/userModal.htm",
                controller: "UserModalCtrl",
                inputs: {
                    title: '新增用户',
                    initVal: {}
                }
            }).then(function (result) {
                if (result) {
                    searchUsers();
                }
            })
        };

        $scope.viewDetails = function (user) {
            $location.path('/users/' + user.id);
        };

        $scope.modify = function (user) {
            udcModal.show({
                templateUrl: "./user/userModal.htm",
                controller: "UserModalCtrl",
                inputs: {
                    title: '修改用户',
                    initVal: user
                }
            }).then(function (result) {
                if (result) {
                    searchUsers();
                }
            })
        };

        $scope.delete = function (user) {
            udcModal.confirm({"title": "删除用户", "message": "是否永久删除用户信息 " + user.name})
                .then(function (result) {
                    if (result) {
                        UserService.deleteUser(user).then(function () {
                            searchUsers();
                        });
                    }
                })
        };

        var searchUsers = function () {
            var queryParams = {
                name: $scope.q.userName,
                pageNo: $scope.pager.getCurPageNo(),
                pageSize: $scope.pager.pageSize
            };

            UserService.retrieveUsers(queryParams).then(function (users) {
                $scope.pager.update($scope.q, users.total, queryParams.pageNo);
                $scope.vm.userList = _.map(users.items, UserService.toViewModel);
            });
        };

        var init = function () {
            searchUsers();
        };

        init();

    }]);

userApp.controller('UserModalCtrl', ['$scope', 'close', 'UserService', 'title', 'initVal', function ($scope, close, UserService, title, initVal) {
    $scope.modalTitle = title;
    $scope.vm = {
        user: {}
    };

    $scope.close = function (result) {
        close(result, 500);
    };

    $scope.submit = function (user) {
        if (user.name != "" && title == "新增用户") {
            UserService.createUser(user).then(function () {
                $scope.close(true);
            })
        } else if (user.name != "" && title == "修改用户") {
            UserService.modifyUser(user).then(function () {
                $scope.close(true);
            })
        }
    };

    var init = function () {
        $scope.vm.user = _.clone(initVal)
    };

    init();
}]);