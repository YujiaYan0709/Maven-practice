package com.donno.nj.controller;

import com.donno.nj.aspect.OperationLog;
import com.donno.nj.constant.Constant;
import com.donno.nj.domain.Bottom;
import com.donno.nj.logger.BusinessLogger;
import com.donno.nj.logger.DebugLogger;
import com.donno.nj.logger.OpLogger;
import com.donno.nj.representation.ListRep;
import com.donno.nj.service.BottomService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static com.donno.nj.util.ParamMapBuilder.paginationParams;
import static com.google.common.collect.Maps.newHashMap;

@RestController
public class BottomController {

    @Autowired
    private BottomService bottomService;

    @RequestMapping(value = "/api/bottoms", method = RequestMethod.GET, produces = "application/json")
    @OperationLog(desc = "获取钢瓶列表")
    public ResponseEntity retrieveBottomers(@RequestParam(value = "name", defaultValue = "") String bottomName,
                                        @RequestParam(value = "orderBy", defaultValue = "updateTime") String orderBy,
                                        @RequestParam(value = "order", defaultValue = "desc") String order,
                                        @RequestParam(value = "pageSize", defaultValue = Constant.PAGE_SIZE) Integer pageSize,
                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        BusinessLogger.log("business");
        OpLogger.log("op log");
        DebugLogger.log("debug");

        Map params = newHashMap(ImmutableMap.of("name", bottomName));
        params.putAll(paginationParams(pageNo, pageSize, orderBy, order));

        List<Bottom> bottoms = bottomService.retrieveBottoms(params);
        Integer total = bottomService.count(params);


        return ResponseEntity.ok(ListRep.assemble(bottoms, total));
    }

    @OperationLog(desc = "创建瓶子")
    @RequestMapping(value = "/api/bottoms", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody Bottom bottom, UriComponentsBuilder ucBuilder) {

        if (bottomService.findBottomByName(bottom.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Bottom newBottom = bottomService.createBottom(bottom);
        URI uri = ucBuilder.path("/api/bottoms/{id}").buildAndExpand(newBottom.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @OperationLog(desc = "删除钢瓶信息")
    @RequestMapping(value = "/api/bottoms/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBottom(@PathVariable("id") Long id) {

        Optional<Bottom> curBottom = bottomService.findById(id);
        if (curBottom.isPresent()) {
            bottomService.deleteBottom(curBottom.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
