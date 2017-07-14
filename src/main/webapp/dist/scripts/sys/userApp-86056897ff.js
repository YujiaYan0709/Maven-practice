'use strict';

var userApp = angular.module('UserApp', ['ui.router', 'CommonModule', 'angularModalService'])
    .config(['$httpProvider', '$stateProvider', '$urlRouterProvider', function($httpProvider, $stateProvider, $urlRouterProvider) {

        // 设置默认显示页面
        $urlRouterProvider.otherwise('/users');

        $stateProvider
            .state('users', {
                url: '/',
                abstract: true,
                views: {
                    "": {templateUrl: '../pages/main.htm'}
                },
                onEnter: function (rootService, NavItem) {
                    rootService.updateActiveNavL2(NavItem.UserCenter.menuItems[0]);
                }
            })
            .state('users.list',{
                url:'users',
                views: {
                    "content@users": {
                        templateUrl: '../pages/user/userList.htm',
                        controller: 'UserListCtrl',
                        resolve: {}
                    }
                }
            })
            .state('users.detail',{
                url:'users/{id: int}',
                views: {
                    "content@users": {
                        templateUrl: '../pages/user/userDetails.htm',
                        controller: 'UserDetailsCtrl',
                        resolve: {
                            UserInfo: function (UserService, $stateParams) {
                                return UserService.getUserInfoById($stateParams.id);
                            }
                        }
                    }
                },
                onEnter: function (rootService, NavItem) {
                    rootService.updateActiveNavL2(NavItem.UserCenter.menuItems[0]);
                }
            });

        $httpProvider.interceptors.push('HttpInterceptor');
    }]).run(['$rootScope', 'rootService', 'NavItem', function ($rootScope, rootService, NavItem) {
        rootService.updateActiveNavLv1(NavItem.UserCenter);
    }]);
