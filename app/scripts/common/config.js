'use strict';

commonModule.constant('URI', {
    'resources': {
        'users': '../../../api/users'
    }
});

commonModule.constant('NavItem', {
    // NavLv1: header, NavLv2: menuItem, NavLv3: subMenuItem
    // note: set menuItem 'href' prop to be empty when it has subMenuItems
    'Home': {
        name: "首页",
        href: "",
        menuItems: []
    },
    'UserCenter': {
        name: "用户中心",
        href: "./userCenter.htm",
        menuItems: [{
            index: 0,
            name: "用户列表",
            href: "./userCenter.htm",
            icon: "fa-group"
        }, {
            index: 1,
            name: "权限管理",
            target: "_blank",
            href: "",
            icon: "fa-gears"
        }]
    },
    'GasCenter': {
        name: "钢瓶管理",
        href: "",
        menuItems: [{
            index: 0,
            name: "",
            href: "./gasBottleCenter.htm",
            icon: "fa-sitemap"
        }, {
            index: 1,
            name: "门店管理",
            target: "_blank",
            href: "",
            icon: "fa-building"
        }]
    },
    'HelpCenter': {
        name: "帮助中心",
        href: "",
        target: "_blank"
    }
});


