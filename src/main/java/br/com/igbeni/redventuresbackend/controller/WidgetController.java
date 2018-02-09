package br.com.igbeni.redventuresbackend.controller;

import br.com.igbeni.redventuresbackend.models.Widget;
import br.com.igbeni.redventuresbackend.models.request.WidgetRequest;
import br.com.igbeni.redventuresbackend.repository.UserRepository;
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
            notes = "This can only be done by the logged in user."
    )
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getWidgets() {
        return ResponseEntity.ok(widgetRepository.findAll());
    }

    @ApiOperation(
            value = "Find widget by ID",
            authorizations = {@Authorization(value = "Authorization", scopes = {})},
            notes = "This can only be done by the logged in user."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid ID supplied"),
                    @ApiResponse(code = 404, message = "Widget not found")
            }
    )
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getWidgetById(@ApiParam(value = "Widget ID", required = true) @PathVariable("id") Integer id) {
        return ResponseEntity.ok(widgetRepository.findOne(id));
    }

    @ApiOperation(
            value = "Create new widget",
            authorizations = {@Authorization(value = "Authorization", scopes = {})},
            notes = "This can only be done by the logged in user."
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
            notes = "This can only be done by the logged in user."
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        widget.setColor(widgetRequest.getColor());
        widget.setInventory(widgetRequest.getInventory());
        widget.setMelts(widgetRequest.isMelts());
        widget.setName(widgetRequest.getName());
        widget.setPrice(widgetRequest.getPrice());

        return ResponseEntity.ok(widgetRepository.save(widget));
    }
}
