package org.example

import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import javax.swing.text.Position
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.jvm.Throws

/*Класс шахматной доски.
*Хранит состояние игрового поля:
*    - мап(доступен только в классах наследниках) с позициями на доске и фигурами на позиции(если есть)
*Имеет методы:
*    - Проверки введенной позиции на наличие е её на шахматной доске
*    - Проверки отдельно каждой координаты(X,Y) в веденной позиции на наличие ее на шахматной доске
*    - Возврата фигуры по введенной позиции (или null если на позиции фигуры нет)
*    - Возврата заполненной шахматной доски(в реализации просто вручную созданный map)
*    - Совершения хода. Внутри метода происходит изменение Map. А так же передается лямда коллбека для записи в классе игры взятой фигуры
*    - Доступа для просмотра Map. Просто возвращает Map
*/

abstract class Chessboard<X,Y,P: FigurePosition<X,Y>,B: Chessboard<X,Y,P,B,F>, F: ChessFigure<X,Y,P,B,F>>() {

    protected val mapFiguresOnChessboard: MutableMap<P, F?> = returnFillChessboard()

    abstract fun checkPositionOnBoard(figurePosition: P): Boolean //проверяет принадлежит ли введенная позиция позиции на доске
    abstract fun checkXCoordinateOnBoard(xCoordinate: X): Boolean
    abstract fun checkYCoordinateOnBoard(yCoordinate: Y): Boolean
    abstract fun returnFigureByPosition(figurePosition: P): F?
    protected abstract fun returnFillChessboard(): MutableMap<P, F?>
    abstract fun makeMove(listPossiblePositions: Set<P>,from: P, to: P, onCapture: (F) -> Unit)// использует метод isMove и если возвращает true то, меняет позиции фигур в mapFiguresOnChessboard и записывает что произошло
    fun returnMapFiguresOnChessboard(): MutableMap<P, F?>{
        return mapFiguresOnChessboard
    }
}

class ClassicChessboard(): Chessboard<Char,Int,ClassicFigurePosition, ClassicChessboard, ClassicChessFigure>() {
    override fun checkPositionOnBoard(figurePosition: ClassicFigurePosition): Boolean {
        return checkXCoordinateOnBoard(figurePosition.xCoordinate)&&checkYCoordinateOnBoard(figurePosition.yCoordinate)
    }
    override fun checkXCoordinateOnBoard(xCoordinate: Char):Boolean {
        val isTrueXCoordinate = if (mapFiguresOnChessboard.any{it.key.xCoordinate == xCoordinate}) true else false
        return isTrueXCoordinate
    }
    override fun checkYCoordinateOnBoard(yCoordinate: Int):Boolean {
        val isTrueYCoordinate = if (mapFiguresOnChessboard.any{it.key.yCoordinate == yCoordinate}) true else false
        return isTrueYCoordinate
    }
    override fun returnFigureByPosition(figurePosition: ClassicFigurePosition): ClassicChessFigure? {
        if (!checkPositionOnBoard(figurePosition)) throw Exception("Какую фигуру тебе вернуть если позиции такой нет?")
        return  mapFiguresOnChessboard[figurePosition]
    }
    override fun returnFillChessboard(): MutableMap<ClassicFigurePosition, ClassicChessFigure?> {
        val startChessboard = mutableMapOf<ClassicFigurePosition, ClassicChessFigure?>(
            // Белые фигуры (ColorPlayer.WHITE)

            // Ладьи белые
            ClassicFigurePosition('A', 1) to RookFigure(ColorPlayer.WHITE),
            ClassicFigurePosition('H', 1) to RookFigure(ColorPlayer.WHITE),

            // Кони белые
            ClassicFigurePosition('B', 1) to KnightFigure(ColorPlayer.WHITE),
            ClassicFigurePosition('G', 1) to KnightFigure(ColorPlayer.WHITE),

            // Слоны белые
            ClassicFigurePosition('C', 1) to BishopFigure(ColorPlayer.WHITE),
            ClassicFigurePosition('F', 1) to BishopFigure(ColorPlayer.WHITE),

            // Ферзь и король белые
            ClassicFigurePosition('D', 1) to QueenFigure(ColorPlayer.WHITE),  // Ферзь на D1
            ClassicFigurePosition('E', 1) to KingFigure(ColorPlayer.WHITE),   // Король на E1

            // Пешки белые (второй ряд)
            //ClassicFigurePosition('A', 2) to PawnFigure(ColorPlayer.WHITE),
            ClassicFigurePosition('B', 2) to PawnFigure(ColorPlayer.WHITE),
            ClassicFigurePosition('C', 2) to PawnFigure(ColorPlayer.WHITE),
            //ClassicFigurePosition('D', 2) to PawnFigure(ColorPlayer.WHITE),
            //ClassicFigurePosition('E', 2) to PawnFigure(ColorPlayer.WHITE),
            ClassicFigurePosition('F', 2) to PawnFigure(ColorPlayer.WHITE),
            ClassicFigurePosition('G', 2) to PawnFigure(ColorPlayer.WHITE),
            ClassicFigurePosition('H', 2) to PawnFigure(ColorPlayer.WHITE),

            // Черные фигуры (ColorPlayer.BLACK)

            // Ладьи черные
            ClassicFigurePosition('A', 8) to RookFigure(ColorPlayer.BLACK),
            ClassicFigurePosition('H', 8) to RookFigure(ColorPlayer.BLACK),

            // Кони черные
            ClassicFigurePosition('B', 8) to KnightFigure(ColorPlayer.BLACK),
            ClassicFigurePosition('G', 8) to KnightFigure(ColorPlayer.BLACK),

            // Слоны черные
            ClassicFigurePosition('C', 8) to BishopFigure(ColorPlayer.BLACK),
            ClassicFigurePosition('F', 8) to BishopFigure(ColorPlayer.BLACK),

            // Ферзь и король черные
            ClassicFigurePosition('D', 8) to QueenFigure(ColorPlayer.BLACK),  // Ферзь на D8
            ClassicFigurePosition('E', 8) to KingFigure(ColorPlayer.BLACK),   // Король на E8

            // Пешки черные (седьмой ряд)
            ClassicFigurePosition('A', 7) to PawnFigure(ColorPlayer.BLACK),
            ClassicFigurePosition('B', 7) to PawnFigure(ColorPlayer.BLACK),
            ClassicFigurePosition('C', 7) to PawnFigure(ColorPlayer.BLACK),
            ClassicFigurePosition('D', 7) to PawnFigure(ColorPlayer.BLACK),
            ClassicFigurePosition('E', 7) to PawnFigure(ColorPlayer.BLACK),
            ClassicFigurePosition('F', 7) to PawnFigure(ColorPlayer.BLACK),
            ClassicFigurePosition('G', 7) to PawnFigure(ColorPlayer.BLACK),
            ClassicFigurePosition('H', 7) to PawnFigure(ColorPlayer.BLACK),

            //Заполнение остальных клеток
            ClassicFigurePosition('A',3) to null,
            ClassicFigurePosition('A',4) to null,
            ClassicFigurePosition('A',5) to null,
            ClassicFigurePosition('A',6) to null,

            ClassicFigurePosition('B',3) to null,
            ClassicFigurePosition('B',4) to null,
            ClassicFigurePosition('B',5) to null,
            ClassicFigurePosition('B',6) to null,

            ClassicFigurePosition('C',3) to null,
            ClassicFigurePosition('C',4) to null,
            ClassicFigurePosition('C',5) to null,
            ClassicFigurePosition('C',6) to null,

            ClassicFigurePosition('D',3) to null,
            ClassicFigurePosition('D',4) to null,
            ClassicFigurePosition('D',5) to null,
            ClassicFigurePosition('D',6) to null,

            ClassicFigurePosition('E',3) to null,
            ClassicFigurePosition('E',4) to null,
            ClassicFigurePosition('E',5) to null,
            ClassicFigurePosition('E',6) to null,

            ClassicFigurePosition('F',3) to null,
            ClassicFigurePosition('F',4) to null,
            ClassicFigurePosition('F',5) to null,
            ClassicFigurePosition('F',6) to null,

            ClassicFigurePosition('G',3) to null,
            ClassicFigurePosition('G',4) to null,
            ClassicFigurePosition('G',5) to null,
            ClassicFigurePosition('G',6) to null,

            ClassicFigurePosition('H',3) to null,
            ClassicFigurePosition('H',4) to null,
            ClassicFigurePosition('H',5) to null,
            ClassicFigurePosition('H',6) to null,

        )
        return startChessboard
    }
    override fun makeMove(
        listPossiblePositions: Set<ClassicFigurePosition>,
        from: ClassicFigurePosition,
        to: ClassicFigurePosition,
        onCapture: (ClassicChessFigure)-> Unit
    ) {
        if (!listPossiblePositions.any{it == to}) {
            MyLogger.log("Вы не можете поставить туда выбранную фигуру")
            println(MyLogger.lastMessage)

        }
        else{
            val figure = returnFigureByPosition(from)
            val captured = returnFigureByPosition(to)
            if (captured!=null){
                onCapture(captured)
            }
            mapFiguresOnChessboard[from] = null
            mapFiguresOnChessboard[to] = figure

            figure?.markMove()
        }


    }
}

