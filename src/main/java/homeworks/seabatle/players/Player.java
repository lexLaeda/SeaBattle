package homeworks.seabatle.players;

import homeworks.seabatle.board.field.Field;

import lombok.Data;

@Data
public abstract class Player  {
    private Field field;
    private String name;

}