package com.wildcodeschool.questSpring02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@SpringBootApplication
public class QuestSpring02Application {

    public static void main(String[] args) {
        SpringApplication.run(QuestSpring02Application.class, args);
    }

    class Doctor {

        private String name;
        private String number;

        public Doctor(String number, String name) {
            this.number = number;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

    }

    class ExtendedDoctor extends Doctor {

        private String ageAtStart;
        private String numberOfEpisodes;

        public ExtendedDoctor(String number, String name, String numberOfEpisodes, String ageAtStart) {
            super(number, name);
            this.numberOfEpisodes = numberOfEpisodes;
            this.ageAtStart = ageAtStart;
        }

        public String getAgeAtStart() {
            return ageAtStart;
        }

        public void setAgeAtStart(String ageAtStart) {
            this.ageAtStart = ageAtStart;
        }

        public String getNumberOfEpisodes() {
            return numberOfEpisodes;
        }

        public void setNumberOfEpisodes(String numberOfEpisodes) {
            this.numberOfEpisodes = numberOfEpisodes;
        }
    }

    @RequestMapping("/doctor/{id}")
    @ResponseBody
    Doctor getDoctor(@PathVariable int id, @RequestParam(required = false) boolean details ) {

        String[][] doctors = {
            {"9", "Christopher Eccleston", "13", "41"},
            {"10", "David Tennant", "47", "3"},
            {"11", "Matt Smith", "44", "27"},
            {"12", "Peter Capaldi", "40", "5"},
            {"13", "Jodie Whittaker", "11", "35"}
        };

        if(id > 9 && id <= 13) {

            if(details) {
                return new ExtendedDoctor(doctors[id - 9][0], doctors[id - 9][1], doctors[id - 9][2], doctors[id - 9][3]);
            }
        
        return new Doctor(doctors[id - 9][0], doctors[id - 9][1]);

        } else if(id >= 1 && id <= 8) {
            throw new ResponseStatusException(HttpStatus.SEE_OTHER);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible de récupérer l'incarnation " + id);
        }
    }
}
