package edu.patronovskiy.studentorder.domain.register;

import java.util.ArrayList;
import java.util.List;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

//результат проверки в городском реестре населения всей семьи
public class AnswerCityRegister {
    private List<AnswerCityRegisterItem> items;

    public void addItem(AnswerCityRegisterItem item) {
        if(items==null) {
            items = new ArrayList<>(10);
        }
        items.add(item);
    }

    public List<AnswerCityRegisterItem> getItems() {
        return items;
    }
}
