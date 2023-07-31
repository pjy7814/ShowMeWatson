package com.watson.business.house.controller;

import com.watson.business.house.dto.houseResponse.HouseListResponse;
import com.watson.business.house.dto.houseResponse.HouseDetailResponse;
import com.watson.business.house.service.HouseService;
import com.watson.business.house.dto.houseRequest.HouseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/house")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;

    @GetMapping("")     // 매물 전체 목록
    public ResponseEntity<List<HouseListResponse>> houseList() {
        return ResponseEntity.status(HttpStatus.OK).body(houseService.findAllHouses());
    }

    @GetMapping("/{id}")  // 매물 상세보기
    public ResponseEntity<HouseDetailResponse> houseDetailsByHouseId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(houseService.findHouseByHouseId(id));
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)    // 매물 게시글 등록
//    @PostMapping("")   // 매물 게시글 등록
    public ResponseEntity<String> houseAdd(@RequestPart List<MultipartFile> file, @RequestPart HouseRequest houseRequest) {

        // access_token으로 realtor_id 가져오는 로직 필요
        String realtorId = "realtor_id";

        houseService.addHouse(file, houseRequest, realtorId);

        return ResponseEntity.status(HttpStatus.CREATED).body("매물등록 완료");
    }

}
