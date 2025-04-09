package com.example.demo.controller

import com.example.demo.dto.AllInventoryInfoDto
import com.example.demo.entity.Inventory1
import com.example.demo.service.*
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.IOException

@RestController
class InventoryController(
    private val inventoryService: InventoryService,
    private val inventory1Service: Inventory1Service,
    private val inventory2Service: Inventory2Service,
    private val inventory3Service: Inventory3Service,
    private val inventory4Service: Inventory4Service,
) {
    companion object {
        const val TAG = "재고"
    }

    @Operation(
        summary = "모든 재고 통계 조회",
        description = "모든 재고 통계 조회",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @GetMapping(value = ["/inventories"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listAllInventoryInfoDto(): ResponseEntity<AllInventoryInfoDto> {
        return ResponseEntity.ok().body(
            inventoryService.listAllInventoryInfoDto()
        )
    }

    @Operation(
        summary = "재고1 생성",
        description = "재고1 생성",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @PostMapping(value = ["/inventory1s"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createInventory1(): ResponseEntity<Inventory1> {
        return ResponseEntity.ok().body(
            inventory1Service.createInventory(1.0)
        )
    }

    @Operation(
        summary = "재고2 생성",
        description = "재고2 생성",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @PostMapping(value = ["/inventory2s"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createInventory2(): ResponseEntity<String> {
        return ResponseEntity.ok().body(
            inventory2Service.createInventory(2.0)
        )
    }

    @Operation(
        summary = "재고3 생성",
        description = "재고3 생성",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @PostMapping(value = ["/inventory3s"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createInventory3(): ResponseEntity<String> {
        return ResponseEntity.ok().body(
            inventory3Service.createInventory(3.0)
        )
    }

    @Operation(
        summary = "재고4 생성",
        description = "재고4 생성",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @PostMapping(value = ["/inventory4s"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createInventory4(): ResponseEntity<String> {
        return ResponseEntity.ok().body(
            inventory4Service.createInventory(4.0)
        )
    }

}
