package group.lab1.Service;


import group.lab1.Model.Producer;
import group.lab1.Repo.ProducerRepo;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ProducerService implements Service<Producer> {

    private ProducerRepo repo;

    public ProducerService(ProducerRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Long> getAll() {
        return repo.findAll().stream().map(producer -> producer.getId()).collect(Collectors.toList());
    }

    @Override
    public Producer save(Producer producer) {
        return repo.save(producer);
    }

    @Override
    public Producer getById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Producer update(Producer producer) {
        return repo.save(producer);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
