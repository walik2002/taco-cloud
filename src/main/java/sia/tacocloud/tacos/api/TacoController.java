package sia.tacocloud.tacos.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.tacos.models.Taco;
import sia.tacocloud.tacos.repos.TacoRepository;

import java.util.Optional;

/*
@RestController отмечает класс для обнаружения механизмом сканирования
сообщает фреймворку Spring, что возвращаемые значения всех методов-обработчиков в  контроллере должны
включаться непосредственно в тело ответа, а не переноситься в модели в представления для отображения
*/
@RestController

/*
 будет обрабатывать запросы, только если запрос клиента содержит заголовок Accept со значением "application/json"
 ограничивает наш API возвратом результатов в формате JSON
*/
@RequestMapping(path = "/api/tacos",produces = "application/json")//produces – формат возвращаемых клиенту данных

/*веб-браузер не позволит вашему клиенту использовать API. Это ограничение можно обойти,
включив заголовки CORS (Cross-Origin Resource Sharing – совместное использование ресурсов между источниками) в ответы сервера. Spring
упрощает применение CORS с помощью аннотации @CrossOrigin.*/
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {

    private TacoRepository tacoRepository;

    public TacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){

        PageRequest page = PageRequest.of(0,12, Sort.by("createdAt").descending());

        return tacoRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")/*
    Фактическое значение в запросе присваивается параметру id, которое извлекается из заполнителя {id} с помощью @PathVariable.*/
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id){

        Optional<Taco> optionalTaco =  tacoRepository.findById(id);

        if(optionalTaco.isPresent()){

            return  new ResponseEntity<>(optionalTaco.get(), HttpStatus.OK);
        }
        return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

    }

    @PostMapping(consumes="application/json")//consumes определяет формат входных данных в запросе
    @ResponseStatus(HttpStatus.CREATED)//POST статус HTTP 201 (CREATED)
    public Taco postTaco(@RequestBody Taco taco) {//@RequestBody, указывающей, что тело запроса должно быть преобразовано в объект Taco и присвоено параметру

        return tacoRepository.save(taco);
    }
}
