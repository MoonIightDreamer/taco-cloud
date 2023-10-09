package tacos.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.TacoOrder;
import tacos.data.OrderRepository;

@RestController
@RequestMapping(path = "/api/orders",
        produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class OrderRestController {

    private final OrderRepository repository;

    public OrderRestController(OrderRepository repository) {
        this.repository = repository;
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public TacoOrder putOrder(@PathVariable("orderId") Long orderId,
                              @RequestBody TacoOrder order) {
        order.setId(orderId);
        return repository.save(order);
    }
}
