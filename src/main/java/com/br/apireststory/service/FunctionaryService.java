package com.br.apireststory.service;

@Service
public class FunctionaryService {
    @Autowired
    private FunctionaryRepository functionaryRepository;

    public boolean existsCPF(String cpf) {
        return functionaryRepository.existsById(cpf);
    }

    public List<Functionary> listEmployees(){
        return functionaryRepository.findAll();
    }

    public Functionary listFunctionaryByCPF(String cpf){
        return functionaryRepository.findById(cpf).orElseThrow(() -> new RuntimeException("Employee not found with this CPF: " + cpf));
    }

    public Optional<Functionary> updateFunctionary(String cpf, Functionary newData) {
        Optional<Functionary> functionaryExists = functionaryRepository.findById(cpf);

        if (functionaryExists.isPresent()) {
            Functionary functionary = functionaryExists.get();

            functionary.setName(newData.getName());
            functionary.setSituation(newData.getSituation());
            functionary.setPosition(newData.getPosition());
            functionary.setPassword(newData.getPassword());
            functionary.setSalary(newData.getSalary());

            functionaryRepository.save(functionary);
            return Optional.of(functionary);
        } else {
            return Optional.empty();
        }
    }

    public void deleteFunctionaryByCPF(String cpf){
        functionaryRepository.deleteById(cpf);
    }
}
