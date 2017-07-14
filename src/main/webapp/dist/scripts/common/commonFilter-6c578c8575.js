'use strict';

commonModule.filter('shortUserName',function(){
    return function(userName){
        return userName && (userName.indexOf('@') > -1)
                    && userName.slice(0, userName.indexOf('@'))
                || userName;
    }
});
