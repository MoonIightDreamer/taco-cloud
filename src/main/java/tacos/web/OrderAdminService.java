package tacos.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import tacos.data.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderAdminService {

    private final OrderRepository repository;

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        repository.deleteAll();
    }
}
