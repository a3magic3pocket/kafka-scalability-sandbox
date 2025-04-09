package com.example.demo.service

import com.example.demo.dto.AllInventoryInfoDto
import com.example.demo.dto.DiffDto
import com.example.demo.dto.InventoryDetailDto
import com.example.demo.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@Service
class InventoryService(
    private val inventory1Repository: Inventory1Repository,
    private val inventory2Repository: Inventory2Repository,
    private val inventory3Repository: Inventory3Repository,
    private val inventory4Repository: Inventory4Repository,
) {

    @Transactional(readOnly = true)
    fun listAllInventoryInfoDto(): AllInventoryInfoDto {
        val inventory1DetailDto = InventoryDetailDto(
            firstInventory = inventory1Repository.findTopByOrderByIdAsc(),
            lastInventory = inventory1Repository.findTopByOrderByIdDesc(),
            count = inventory1Repository.count()
        )
        val inventory1DiffSec =
            if (inventory1DetailDto.firstInventory?.createdAt == null || inventory1DetailDto.lastInventory?.createdAt == null) {
                null
            } else {
                Duration.between(
                    inventory1DetailDto.firstInventory.createdAt,
                    inventory1DetailDto.lastInventory.createdAt
                ).seconds
            }

        val inventory2DetailDto = InventoryDetailDto(
            firstInventory = inventory2Repository.findTopByOrderByIdAsc(),
            lastInventory = inventory2Repository.findTopByOrderByIdDesc(),
            count = inventory2Repository.count()
        )
        val inventory2DiffSec =
            if (inventory2DetailDto.firstInventory?.createdAt == null || inventory2DetailDto.lastInventory?.createdAt == null) {
                null
            } else {
                Duration.between(
                    inventory2DetailDto.firstInventory.createdAt,
                    inventory2DetailDto.lastInventory.createdAt
                ).seconds
            }

        val inventory3DetailDto = InventoryDetailDto(
            firstInventory = inventory3Repository.findTopByOrderByIdAsc(),
            lastInventory = inventory3Repository.findTopByOrderByIdDesc(),
            count = inventory3Repository.count()
        )
        val inventory3DiffSec =
            if (inventory3DetailDto.firstInventory?.createdAt == null || inventory3DetailDto.lastInventory?.createdAt == null) {
                null
            } else {
                Duration.between(
                    inventory3DetailDto.firstInventory.createdAt,
                    inventory3DetailDto.lastInventory.createdAt
                ).seconds
            }

        val inventory4DetailDto = InventoryDetailDto(
            firstInventory = inventory4Repository.findTopByOrderByIdAsc(),
            lastInventory = inventory4Repository.findTopByOrderByIdDesc(),
            count = inventory4Repository.count()
        )
        val inventory4DiffSec =
            if (inventory4DetailDto.firstInventory?.createdAt == null || inventory4DetailDto.lastInventory?.createdAt == null) {
                null
            } else {
                Duration.between(
                    inventory4DetailDto.firstInventory.createdAt,
                    inventory4DetailDto.lastInventory.createdAt
                ).seconds
            }

        return AllInventoryInfoDto(
            inventory1 = DiffDto(
                data = inventory1DetailDto,
                diffSec = inventory1DiffSec
            ),
            inventory2 = DiffDto(
                data = inventory2DetailDto,
                diffSec = inventory2DiffSec
            ),
            inventory3 = DiffDto(
                data = inventory3DetailDto,
                diffSec = inventory3DiffSec
            ),
            inventory4 = DiffDto(
                data = inventory4DetailDto,
                diffSec = inventory4DiffSec
            ),
        )
    }
}