package br.com.igbeni.redventuresbackend.repository;

import br.com.igbeni.redventuresbackend.models.Widget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WidgetRepository extends JpaRepository<Widget, Integer> {
}
