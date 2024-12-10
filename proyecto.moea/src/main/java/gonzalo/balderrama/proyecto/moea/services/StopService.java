package gonzalo.balderrama.proyecto.moea.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gonzalo.balderrama.proyecto.moea.model.dto.StopDTO;
import gonzalo.balderrama.proyecto.moea.model.mapper.StopMapper;
import gonzalo.balderrama.proyecto.moea.services.repositories.StopRepository;


@Service
public class StopService {
	protected final StopRepository repository;
	protected final StopMapper mapper;

	public StopService(StopRepository repository, StopMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public Page<StopDTO> get(String orderField, String orderCriterial, Integer pageNumber, Integer pageSize) {
		Pageable page;

		if (orderCriterial.equalsIgnoreCase("desc")) {
			page = PageRequest.of(pageNumber, pageSize, Sort.by(orderField).descending());
		}else{
			page = PageRequest.of(pageNumber, pageSize, Sort.by(orderField).ascending());
		}

		return repository.findAll(page).map(this.mapper::toDTO);
	}
}