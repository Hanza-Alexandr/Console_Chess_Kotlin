package org.example
/*Класс позиции фигуры
*позиция фигуры это не одна, а минимум две переменные.
* И пускай будет возможность добавить еще одну координату и по этому не цивильный дата класс
* Свойства:
*   - позиция X
*   - позиция Y
* Переопределены базовые методы сравнения и hash кода.
* Т.к позициям с одинаковыми координатами по определению существовать не может.
* А если брать два экземпляра с одинаковыми данными у них будет разный хещ код хотя позиция одна и та же.
* Поэтому сравниваем именно по координатам.
 */
abstract class FigurePosition<X,Y>(_xCoordinate: X, _yCoordinate: Y) {
     val xCoordinate: X = _xCoordinate
     val yCoordinate: Y = _yCoordinate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FigurePosition<X, Y>) return false
        return xCoordinate == other.xCoordinate &&
                yCoordinate == other.yCoordinate
    }
    override fun hashCode(): Int {
        var result = xCoordinate.hashCode()
        result = 31 * result + yCoordinate.hashCode()
        return result
    }
}

class ClassicFigurePosition(_xCoordinate: Char, _yCoordinate: Int): FigurePosition<Char, Int>(_xCoordinate,_yCoordinate){
    override fun equals(other: Any?): Boolean =  super.equals(other) && other is ClassicFigurePosition
    override fun hashCode(): Int = super.hashCode()
}