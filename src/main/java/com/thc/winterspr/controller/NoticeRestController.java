package com.thc.winterspr.controller;

import com.thc.winterspr.dto.NoticeDto;
import com.thc.winterspr.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/notice")
@RestController
public class NoticeRestController {

    private final NoticeService noticeService;
    public NoticeRestController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping("")
    public ResponseEntity<NoticeDto.CreateResDto> create(@RequestBody NoticeDto.CreateReqDto params) {
        //return ResponseEntity.status(HttpStatus.CREATED).body(noticeService.create(params));
        return ResponseEntity.ok(noticeService.create(params));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody NoticeDto.UpdateReqDto params) {
        noticeService.update(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody NoticeDto.UpdateReqDto params) {
        noticeService.delete(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("")
    public ResponseEntity<NoticeDto.DetailResDto> detail(NoticeDto.DetailReqDto params) {
        return ResponseEntity.ok(noticeService.detail(params));
    }
    @GetMapping("/list")
    public ResponseEntity<List<NoticeDto.DetailResDto>> list(NoticeDto.ListReqDto params) {
        return ResponseEntity.ok(noticeService.list(params));
    }

    @GetMapping("/pagedList")
    public ResponseEntity<NoticeDto.PagedListResDto> pagedList(NoticeDto.PagedListReqDto params) {
        return ResponseEntity.ok(noticeService.pagedList(params));
    }

    @GetMapping("/scrollList")
    public ResponseEntity<List<NoticeDto.DetailResDto>> scrollList(NoticeDto.ScrollListReqDto params) {
        return ResponseEntity.ok(noticeService.scrollList(params));
    }
}

/*ResponseEntity 는 body랑 status로 구성됨*/