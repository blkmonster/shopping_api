package com.lyc.yl.controller;

import com.lyc.yl.entity.History;
import com.lyc.yl.entity.LitemallStorage;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.HistoryService;
import com.lyc.yl.service.LitemallStorageService;
import com.lyc.yl.util.storage.StorageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;


/**
 * @author zhaoxin
 */
@RestController
@RequestMapping("/wx/storage")
public class WxStorageController {

    @Resource
    private StorageService storageService;
    @Resource
    private LitemallStorageService litemallStorageService;
    @Resource
    private HistoryService historyService;

    @PostMapping("/create")
    public Object create(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        LitemallStorage litemallStorage = storageService.store(file.getInputStream(), file.getSize(),
                file.getContentType(), originalFilename);
        return ResponseUtil.ok(litemallStorage);
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallStorage litemallStorage) {
        String key = litemallStorage.getKey();
        if (StringUtils.isEmpty(key)) {
            return ResponseUtil.badArgument();
        }
        litemallStorageService.deleteByKey(key);
        storageService.delete(key);
        return ResponseUtil.ok();
    }

    /**
     * 保存搜索历史
     *
     * @param history
     * @return
     */
    @PostMapping("/addHistory")
    public Object addHistory(@RequestBody History history) {
        if (history.getUserId() == null || history.getKeyword() == null || "".equals(history.getKeyword())) {
            return ResponseUtil.badArgument();
        }
        historyService.insertOrUpdate(history);
        return ResponseUtil.ok();
    }


}
