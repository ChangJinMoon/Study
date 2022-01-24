package jin.pratice.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller,ResponseBody 등등을 어노테이션(Annotation)이라고 한다
//자세한 설명 참조 - https://velog.io/@gillog/Spring-Annotation-정리
@Controller
public class HelloController {

    @GetMapping("hello")//get 형식으로 url에 적힌 hello와 매칭
    public String hello(Model model){

        model.addAttribute("name","문창진");//th 키워드에서 name 키를 가지는 라인에 해당 데이터 전송

        return "hello";//Resource/templates에 있는 html파일 중 실행 시킬 페이지 이름명을 return -> hello.html이 실행됨
        //return 받은 후에 뷰 리졸버가 화면을 찾아 처리 -> return 받은 hello를 'resources:templates/' + {ViewName} + '.html' 형식으로 매핑한다
    };

    @GetMapping("hello-mvc")
    public String hello_mvc(@RequestParam(value = "name", defaultValue = "Nan") String name, Model model){
        //command + p 함수 매개변수 보기
        model.addAttribute("name",name);
        return "hello_mvc";
    }

    /*
    * 2022/01/24 실습
    */

    @GetMapping("hello-string")
    @ResponseBody // http 바디에 직접 데이터를 삽입해주겠다
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
        // 결과 - html 페이지가 아닌 get으로 얻은 데이터 name과 hello의 조합이 그대로 페이지에 적힌다
        //해당 페이지를 소스로 보아도 그대로 hello + name 만 들어가 있고 태그등등은 없다. = html페이지가 아닌 것
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("value") String name){
        Hello hello = new Hello();//command+shift+enter => ; 자동 완성 및 줄 바꾸기
        hello.setName(name);
        return hello;
        //객체를 return하면 자동으로 json 형식으로 변환 후 페이지에 적힌다. 기본
        //@ResponseBody 어노테이션을 받으면 뷰리졸버로 보내는 것이 아닌 HttpMessageConverter에 보낸다.
        //단순 String이면 StringConverter에 객체가 오면 JsonConverter(MappingJackson2HttpMessageConverter)가 작동
        //참조 - MappingJackson -> 객체를 json으로 바꿔주는 라이브러리
    }

    static class Hello{
        private String name;
        public String getName() {//property 접근 방식
            return name;
        }

        public void setName(String name) {
            this.name = "Hello, " + name;
        }
    }
}
