/*
 * Copyright  (C) 2018 Iggor Benicio A. S. Alves.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Iggor Benicio A. S. Alves
 */

package br.com.igbeni.redventuresbackend.controller;

import br.com.igbeni.redventuresbackend.models.User;
import br.com.igbeni.redventuresbackend.repository.UserRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
@Api(
        value = "/users",
        description = "Operations about users",
        tags = "Users",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(
            value = "Find all users",
            authorizations = {@Authorization(value = "Authorization", scopes = {})},
            notes = "This can only be done by the logged in user.",
            response = User.class,
            responseContainer = "List"
    )
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @ApiOperation(
            value = "Find user by ID",
            authorizations = {@Authorization(value = "Authorization", scopes = {})},
            notes = "This can only be done by the logged in user.",
            response = User.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid ID supplied"),
                    @ApiResponse(code = 404, message = "User not found")
            }
    )
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getUserById(@ApiParam(value = "User ID", required = true) @PathVariable("id") Integer id) {
        User user = userRepository.findOne(id);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(user);
    }
}
