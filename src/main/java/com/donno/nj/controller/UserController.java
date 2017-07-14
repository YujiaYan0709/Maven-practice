package com.donno.nj.controller;

import com.donno.nj.aspect.OperationLog;
import com.donno.nj.constant.Constant;
import com.donno.nj.domain.User;
import com.donno.nj.logger.BusinessLogger;
import com.donno.nj.logger.DebugLogger;
import com.donno.nj.logger.OpLogger;
import com.donno.nj.representation.ListRep;
import com.donno.nj.service.UserService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static com.donno.nj.util.ParamMapBuilder.paginationParams;
import static com.google.common.collect.Maps.newHashMap;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = "application/json")
    @OperationLog(desc = "获取用户列表")
    public ResponseEntity retrieveUsers(@RequestParam(value = "name", defaultValue = "") String userName,
                                        @RequestParam(value = "roles", defaultValue = "") String roles,
                                        @RequestParam(value = "orderBy", defaultValue = "updateTime") String orderBy,
                                        @RequestParam(value = "order", defaultValue = "desc") String order,
                                        @RequestParam(value = "pageSize", defaultValue = Constant.PAGE_SIZE) Integer pageSize,
                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        BusinessLogger.log("business");
        OpLogger.log("op log");
        DebugLogger.log("debug");

        Map params = newHashMap(ImmutableMap.of("name", userName));
        params.putAll(paginationParams(pageNo, pageSize, orderBy, order));

        List<User> users = userService.retrieveUsers(params);
        Integer total = userService.count(params);


        return ResponseEntity.ok(ListRep.assemble(users, total));
    }

    @OperationLog(desc = "创建用户")
    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {

        if (userService.findUserByName(user.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User newUser = userService.createUser(user);
        URI uri = ucBuilder.path("/api/users/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {

        Optional<User> curUser = userService.findById(id);
        if (curUser.isPresent()) {
            return ResponseEntity.ok(curUser.get());
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @OperationLog(desc = "修改用户信息")
    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody User newUser) {

        Optional<User> curUser = userService.findById(id);
        if (curUser.isPresent()) {
            userService.updateUser(curUser.get(), newUser);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @OperationLog(desc = "删除用户信息")
    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {

        Optional<User> curUser = userService.findById(id);
        if (curUser.isPresent()) {
            userService.deleteUser(curUser.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
