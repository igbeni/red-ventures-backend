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

import br.com.igbeni.redventuresbackend.models.Widget;
import br.com.igbeni.redventuresbackend.models.request.WidgetRequest;
import br.com.igbeni.redventuresbackend.repository.WidgetRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "widgets")
@Api(
        value = "/widgets",
        description = "Operations about widgets",
        tags = "Widgets",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class WidgetController {

    @Autowired
    private WidgetRepository widgetRepository;

    @ApiOperation(
            value = "Find all widgets",
            authorizations = {@Authorization(value = "Authorization", scopes = {})},
            notes = "This can only be done by the logged in user.",
            response = Widget.class,
            responseContainer = "List"
    )
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getWidgets() {
        return ResponseEntity.ok(widgetRepository.findAll());
    }

    @ApiOperation(
            value = "Find widget by ID",
            authorizations = {@Authorization(value = "Authorization", scopes = {})},
            notes = "This can only be done by the logged in user.",
            response = Widget.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid ID supplied"),
                    @ApiResponse(code = 404, message = "Widget not found")
            }
    )
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getWidgetById(@ApiParam(value = "Widget ID", required = true) @PathVariable("id") Integer id) {
        Widget widget = widgetRepository.findOne(id);

        if (widget == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(widget);
    }

    @ApiOperation(
            value = "Create new widget",
            authorizations = {@Authorization(value = "Authorization", scopes = {})},
            notes = "This can only be done by the logged in user.",
            response = Widget.class
    )
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createWidget(@RequestBody WidgetRequest widgetRequest) {
        Widget widget = new Widget();
        widget.setColor(widgetRequest.getColor());
        widget.setInventory(widgetRequest.getInventory());
        widget.setMelts(widgetRequest.isMelts());
        widget.setName(widgetRequest.getName());
        widget.setPrice(widgetRequest.getPrice());

        return ResponseEntity.ok(widgetRepository.save(widget));
    }

    @ApiOperation(
            value = "Update existing widget",
            authorizations = {@Authorization(value = "Authorization", scopes = {})},
            notes = "This can only be done by the logged in user.",
            response = Widget.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid ID supplied"),
                    @ApiResponse(code = 404, message = "Widget not found")
            }
    )
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateWidget(@ApiParam(value = "Widget ID", required = true) @PathVariable("id") Integer id,
                                          @ApiParam(value = "Updated widget object", required = true) @RequestBody WidgetRequest widgetRequest) {
        Widget widget = widgetRepository.findOne(id);
        if (widget == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        widget.setColor(widgetRequest.getColor());
        widget.setInventory(widgetRequest.getInventory());
        widget.setMelts(widgetRequest.isMelts());
        widget.setName(widgetRequest.getName());
        widget.setPrice(widgetRequest.getPrice());

        return ResponseEntity.ok(widgetRepository.save(widget));
    }
}
