package com.example.bookspresso.api.Aladen;

import com.example.bookspresso.batch.Aladin.BookRegisterJobConfig;
import com.example.bookspresso.service.aladen.AladinService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AladinAPI {
    private final AladinService aladinService;
    private final JobLauncher jobLauncher;
    private final BookRegisterJobConfig bookRegisterJobConfig;
    @GetMapping("/batch")
    public void batch(){
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            /*
             JobLauncher 객체의 run() 를 통해 Job을 실행하여 배치 처리를 할 수 있다.
            파라미터로 실행할 Job 객체와 JobParameters 객체가 필요하다.
            Job 객체는 배치 처리할 작업을 가지고 있고
            JobParameters 는 Job을 여러 번 실행하는 상황에서 각 실행을 구분하기 위한 값이다.
            중복되는 값이 들어가면 오류가 날 수 있기 때문에 보통 중복되지 않는 값을 넣기위해 시간을 이용하는
            경우가 많다.
             */
            jobLauncher.run(bookRegisterJobConfig.apiJob(),jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/aladen")
    public String aladen() {
        aladinService.getApialadinMain();
        return "aladen";
    }
}
