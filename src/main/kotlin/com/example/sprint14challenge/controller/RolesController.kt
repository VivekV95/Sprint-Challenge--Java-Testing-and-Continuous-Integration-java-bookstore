package com.example.sprint14challenge.controller

import com.example.sprint14challenge.model.Role
import com.example.sprint14challenge.service.RoleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import java.net.URI
import java.net.URISyntaxException

@Component
@RestController
@RequestMapping("/roles")
class RolesController {
    @Autowired
    lateinit var roleService: RoleService

    @GetMapping(value = ["/roles"], produces = ["application/json"])
    fun listRoles(request: HttpServletRequest): ResponseEntity<*> {
        logger.trace(request.requestURI + " accessed")

        val allRoles = roleService.findAll()
        return ResponseEntity(allRoles, HttpStatus.OK)
    }


    @GetMapping(value = ["/role/{roleId}"], produces = ["application/json"])
    fun getRole(request: HttpServletRequest, @PathVariable roleId: Long?): ResponseEntity<*> {
        logger.trace(request.requestURI + " accessed")

        val r = roleService.findRoleById(roleId!!)
        return ResponseEntity(r, HttpStatus.OK)
    }


    @PostMapping(value = ["/role"])
    @Throws(URISyntaxException::class)
    fun addNewRole(request: HttpServletRequest, @Valid @RequestBody newRole: Role): ResponseEntity<*> {
        logger.trace(request.requestURI + " accessed")

        val newRole = roleService.save(newRole)

        // set the location header for the newly created resource
        val responseHeaders = HttpHeaders()
        val newRoleURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{roleid}")
                .buildAndExpand(newRole.roleid)
                .toUri()
        responseHeaders.location = newRoleURI

        return ResponseEntity<Any>(null, responseHeaders, HttpStatus.CREATED)
    }


    @DeleteMapping("/role/{id}")
    fun deleteRoleById(request: HttpServletRequest, @PathVariable id: Long): ResponseEntity<*> {
        logger.trace(request.requestURI + " accessed")

        roleService.delete(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    companion object {

        private val logger = LoggerFactory.getLogger(RolesController::class.java)
    }
}
