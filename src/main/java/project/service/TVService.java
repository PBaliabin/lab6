package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.model.TV;
import project.model.TVDTO;
import project.repository.TVRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TVService {
    private final TVRepository tvRepository;

    public void add(int voltage, int diagonal, String brand, String connectionType, String model) {
        tvRepository.save(new TV(voltage, diagonal, brand, connectionType, model));
    }

    public void add(TVDTO tvdto) {
        add(
                tvdto.getVoltage(),
                tvdto.getDiagonal(),
                tvdto.getBrand(),
                tvdto.getConnectionType(),
                tvdto.getModel()
        );
    }

    public TV findByID(int id) {
        return tvRepository.findById(id).orElseThrow();
    }

    public void delete(int id) {
        tvRepository.deleteById(id);
    }

    public Iterable<TV> findAll() {
        return tvRepository.findAll();
    }

    public Iterable<TV> findAllByDiagonalGreaterThan(int diagonal) {
        return tvRepository.findTVSByDiagonalGreaterThan(diagonal);
    }

    public void update(TVDTO tvdto) {
        TV tv = tvRepository.findById(tvdto.getId()).orElseThrow(NoSuchElementException::new);
        tv.setVoltage(tvdto.getVoltage());
        tv.setDiagonal(tvdto.getDiagonal());
        tv.setBrand(tvdto.getBrand());
        tv.setConnectionType(tvdto.getConnectionType());
        tv.setModel(tvdto.getModel());
        tvRepository.save(tv);
    }
}
