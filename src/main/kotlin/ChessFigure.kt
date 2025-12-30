package org.example

/* Класс фигур
*Класс шахматных фигур. Хранит в себе состояние фигуры и и проверку куда может сделать ход данная фигура
* Свойства:
*   -тип фигуры. Текстом. классическая из вархамера или еще что нибудь
*   -символьное представление. Символ который будет отображаться в консоли
*   -цвет фигуры
* Методы:
*   -главный метод. Возвращает список позиций(или null если позиций для хода вообще нет)куда фигуру можно поставить(исходя из правил игры) относительно той позиции где она находится и фигур который находятся на поле
*/
abstract class ChessFigure<X,Y,P: FigurePosition<X,Y>, B: Chessboard<X,Y,P,B,F>, F: ChessFigure<X,Y,P,B,F> >(){
    abstract val typeFigures: String
    abstract val charAliasOnChessboard: Char
    abstract val colorFigure: ColorPlayer

    abstract fun whereCanMakeMove(currentPlayer: ColorPlayer, currentPosition: P, chessboard: B): Set<P>?
    abstract fun markMove()
}

abstract class ClassicChessFigure(_color: ColorPlayer): ChessFigure<Char,Int,ClassicFigurePosition, ClassicChessboard, ClassicChessFigure>() {
    abstract override fun whereCanMakeMove(currentPlayer: ColorPlayer,currentPosition: ClassicFigurePosition, chessboard: ClassicChessboard): Set<ClassicFigurePosition>?
    override val colorFigure= _color
    override val typeFigures = "Classic"
}

class KingFigure(_color: ColorPlayer) : ClassicChessFigure(_color) {
    override val charAliasOnChessboard: Char = if (colorFigure == ColorPlayer.WHITE) '♔' else '♚'
    override fun whereCanMakeMove(
        currentPlayer: ColorPlayer,
        currentPosition: ClassicFigurePosition,
        chessboard: ClassicChessboard
    ): Set<ClassicFigurePosition>? {
        val list = mutableListOf<ClassicFigurePosition>()
        fun calculateXPosition(){
            val x = currentPosition.xCoordinate +1
            var figure: ClassicChessFigure?

            if (chessboard.checkPositionOnBoard(ClassicFigurePosition(x,currentPosition.yCoordinate))){
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(x,currentPosition.yCoordinate))
                if(figure ==null) {
                    list.add(ClassicFigurePosition(x,currentPosition.yCoordinate))
                }
                else{
                    if (figure.colorFigure != currentPlayer){
                        list.add(ClassicFigurePosition(x,currentPosition.yCoordinate))

                    }
                }
            }
        }
        fun calculateMinusXPosition(){
            val x = currentPosition.xCoordinate -1
            var figure: ClassicChessFigure?

            if (chessboard.checkPositionOnBoard(ClassicFigurePosition(x,currentPosition.yCoordinate))){
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(x,currentPosition.yCoordinate))
                if(figure ==null) {
                    list.add(ClassicFigurePosition(x,currentPosition.yCoordinate))
                }
                else{
                    if (figure.colorFigure != currentPlayer){
                        list.add(ClassicFigurePosition(x,currentPosition.yCoordinate))

                    }
                }
            }
        }
        fun calculateYPosition(){
            val y = currentPosition.yCoordinate +1
            var figure: ClassicChessFigure?

            if (chessboard.checkPositionOnBoard(ClassicFigurePosition(currentPosition.xCoordinate,y))){
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(currentPosition.xCoordinate,y))
                if(figure ==null) {
                    list.add(ClassicFigurePosition(currentPosition.xCoordinate,y))
                }
                else{
                    if (figure.colorFigure != currentPlayer){
                        list.add(ClassicFigurePosition(currentPosition.xCoordinate,y))

                    }
                }
            }
        }
        fun calculateMinusYPosition(){
            val y = currentPosition.yCoordinate -1
            var figure: ClassicChessFigure?

            if (chessboard.checkPositionOnBoard(ClassicFigurePosition(currentPosition.xCoordinate,y))){
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(currentPosition.xCoordinate,y))
                if(figure ==null) {
                    list.add(ClassicFigurePosition(currentPosition.xCoordinate,y))
                }
                else{
                    if (figure.colorFigure != currentPlayer){
                        list.add(ClassicFigurePosition(currentPosition.xCoordinate,y))

                    }
                }
            }
        }


        fun calculateXYPosition(){
            val x = currentPosition.xCoordinate +1
            val y = currentPosition.yCoordinate +1
            var figure: ClassicChessFigure?

            if (chessboard.checkPositionOnBoard(ClassicFigurePosition(x,y))){
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(x,y))
                if(figure ==null) {
                    list.add(ClassicFigurePosition(x,y))
                }
                else{
                    if (figure.colorFigure != currentPlayer){
                        list.add(ClassicFigurePosition(x,y))
                    }
                }
            }
        }
        fun calculateMinusXYPosition(){
            val x = currentPosition.xCoordinate -1
            val y = currentPosition.yCoordinate +1
            var figure: ClassicChessFigure?

            if (chessboard.checkPositionOnBoard(ClassicFigurePosition(x,y))){
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(x,y))
                if(figure ==null) {
                    list.add(ClassicFigurePosition(x,y))
                }
                else{
                    if (figure.colorFigure != currentPlayer){
                        list.add(ClassicFigurePosition(x,y))
                    }
                }
            }
        }
        fun calculateXMinusYPosition(){
            val x = currentPosition.xCoordinate +1
            val y = currentPosition.yCoordinate -1
            var figure: ClassicChessFigure?

            if (chessboard.checkPositionOnBoard(ClassicFigurePosition(x,y))){
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(x,y))
                if(figure ==null) {
                    list.add(ClassicFigurePosition(x,y))
                }
                else{
                    if (figure.colorFigure != currentPlayer){
                        list.add(ClassicFigurePosition(x,y))
                    }
                }
            }
        }
        fun calculateMinusXMinusYPosition(){
            val x = currentPosition.xCoordinate -1
            val y = currentPosition.yCoordinate -1
            var figure: ClassicChessFigure?

            if (chessboard.checkPositionOnBoard(ClassicFigurePosition(x,y))){
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(x,y))
                if(figure ==null) {
                    list.add(ClassicFigurePosition(x,y))
                }
                else{
                    if (figure.colorFigure != currentPlayer){
                        list.add(ClassicFigurePosition(x,y))
                    }
                }
            }
        }

        calculateXPosition()
        calculateMinusXPosition()
        calculateYPosition()
        calculateMinusYPosition()

        calculateXYPosition()
        calculateMinusXYPosition()
        calculateXMinusYPosition()
        calculateMinusXMinusYPosition()

        return if (list.isEmpty()) null else list.toSet()
    }

    override fun markMove() {
    }
}
class QueenFigure(_color: ColorPlayer): ClassicChessFigure(_color) {
    override val charAliasOnChessboard: Char = if (colorFigure == ColorPlayer.WHITE) '♕' else '♛'
    override fun whereCanMakeMove(
        currentPlayer: ColorPlayer,
        currentPosition: ClassicFigurePosition,
        chessboard: ClassicChessboard
    ): Set<ClassicFigurePosition>? {
        TODO("Not yet implemented")
    }

    override fun markMove() {
    }

}
class RookFigure(_color: ColorPlayer): ClassicChessFigure(_color) {
    override val charAliasOnChessboard: Char = if (colorFigure == ColorPlayer.WHITE) '♖' else '♜'
    override fun whereCanMakeMove(
        currentPlayer: ColorPlayer,
        currentPosition: ClassicFigurePosition,
        chessboard: ClassicChessboard
    ): Set<ClassicFigurePosition>? {
        val list: MutableList<ClassicFigurePosition> = mutableListOf()

        fun calculateXPosition(){
            var x = currentPosition.xCoordinate +1
            var figure: ClassicChessFigure?
            while (true){
                if (!chessboard.checkPositionOnBoard(ClassicFigurePosition(x,currentPosition.yCoordinate))) break //если такая позиция отсутствует ничего не записываем и выходим
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(x,currentPosition.yCoordinate))
                if(figure !=null) {
                    if (figure.colorFigure == currentPlayer){
                        break
                    }
                    else{
                        list.add(ClassicFigurePosition(x,currentPosition.yCoordinate))
                        break
                    }
                }
                else{
                    list.add(ClassicFigurePosition(x,currentPosition.yCoordinate))
                    x++
                    continue
                }
            }
        }
        fun calculateMinusXPosition(){
            var x = currentPosition.xCoordinate -1
            var figure: ClassicChessFigure?
            while (true){
                if (!chessboard.checkPositionOnBoard(ClassicFigurePosition(x,currentPosition.yCoordinate))) break //если такая позиция отсутствует ничего не записываем и выходим
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(x,currentPosition.yCoordinate))
                if(figure !=null) {
                    if (figure.colorFigure == currentPlayer){
                        break
                    }
                    else{
                        list.add(ClassicFigurePosition(x,currentPosition.yCoordinate))
                        break
                    }
                }
                else{
                    list.add(ClassicFigurePosition(x,currentPosition.yCoordinate))
                    x--
                    continue
                }
            }
        }
        fun calculateYPosition(){
            var y = currentPosition.yCoordinate +1
            var figure: ClassicChessFigure?
            while (true){
                if (!chessboard.checkPositionOnBoard(ClassicFigurePosition(currentPosition.xCoordinate,y))) break //если такая позиция отсутствует ничего не записываем и выходим
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(currentPosition.xCoordinate,y))
                if(figure !=null) {
                    if (figure.colorFigure == currentPlayer){
                        break
                    }
                    else{
                        list.add(ClassicFigurePosition(currentPosition.xCoordinate,y))
                        break
                    }
                }
                else{
                    list.add(ClassicFigurePosition(currentPosition.xCoordinate,y))
                    y++
                    continue
                }
            }
        }
        fun calculateMinusYPosition(){
            var y = currentPosition.yCoordinate -1
            var figure: ClassicChessFigure?
            while (true){
                if (!chessboard.checkPositionOnBoard(ClassicFigurePosition(currentPosition.xCoordinate,y))) break //если такая позиция отсутствует ничего не записываем и выходим
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(currentPosition.xCoordinate,y))
                if(figure !=null) {
                    if (figure.colorFigure == currentPlayer){
                        break
                    }
                    else{
                        list.add(ClassicFigurePosition(currentPosition.xCoordinate,y))
                        break
                    }
                }
                else{
                    list.add(ClassicFigurePosition(currentPosition.xCoordinate,y))
                    y--
                    continue
                }
            }
        }


        calculateXPosition()
        calculateMinusXPosition()
        calculateYPosition()
        calculateMinusYPosition()

        return if (list.isEmpty()) null else list.toSet()
    }

    override fun markMove() {
    }

}
class BishopFigure(_color: ColorPlayer): ClassicChessFigure(_color) {
    override val charAliasOnChessboard: Char = if (colorFigure == ColorPlayer.WHITE) '♗' else '♝'
    override fun whereCanMakeMove(
        currentPlayer: ColorPlayer,
        currentPosition: ClassicFigurePosition,
        chessboard: ClassicChessboard
    ): Set<ClassicFigurePosition>? {
        TODO("Not yet implemented")
    }

    override fun markMove() {
    }

}
class KnightFigure(_color: ColorPlayer): ClassicChessFigure(_color) {
    override val charAliasOnChessboard: Char = if (colorFigure == ColorPlayer.WHITE) '♘' else '♞'
    override fun whereCanMakeMove(
        currentPlayer: ColorPlayer,
        currentPosition: ClassicFigurePosition,
        chessboard: ClassicChessboard
    ): Set<ClassicFigurePosition>? {
        TODO("Not yet implemented")
    }

    override fun markMove() {

    }

}
class PawnFigure(_color: ColorPlayer): ClassicChessFigure(_color) {
    var isFirstMove = true
    override val charAliasOnChessboard: Char = if (colorFigure == ColorPlayer.WHITE) '♙' else '♟'
    override fun whereCanMakeMove(
        currentPlayer: ColorPlayer,
        currentPosition: ClassicFigurePosition,
        chessboard: ClassicChessboard
    ): Set<ClassicFigurePosition>? {
        val list = mutableListOf<ClassicFigurePosition>()

        fun calculateYPosition(){
            var counter = 1
            if (isFirstMove) counter =2

            var y = currentPosition.yCoordinate +1
            var figure: ClassicChessFigure?

            for (i in 1..counter){
                if (chessboard.checkPositionOnBoard(ClassicFigurePosition(currentPosition.xCoordinate,y))){
                    figure = chessboard.returnFigureByPosition(ClassicFigurePosition(currentPosition.xCoordinate,y))
                    if(figure ==null) {
                        list.add(ClassicFigurePosition(currentPosition.xCoordinate,y))
                    }
                }
                y++
            }
        }
        fun calculateXYPosition(){
            val x = currentPosition.xCoordinate +1
            val y = currentPosition.yCoordinate +1
            val figure: ClassicChessFigure?


            if (chessboard.checkPositionOnBoard(ClassicFigurePosition(x,y))) {
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(x,y))
                val isEnemyAtPosition = figure!=null&&figure.colorFigure != currentPlayer

                if (isEnemyAtPosition) {
                    list.add(ClassicFigurePosition(x, y))
                }
            }
        }
        fun calculateMinusXYPosition(){
            val x = currentPosition.xCoordinate -1
            val y = currentPosition.yCoordinate +1
            val figure: ClassicChessFigure?

            if (chessboard.checkPositionOnBoard(ClassicFigurePosition(x,y))) {
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(x,y))
                val isEnemyAtPosition = figure!=null&&figure.colorFigure != currentPlayer

                if (isEnemyAtPosition) {
                    list.add(ClassicFigurePosition(x, y))
                }
            }
        }

        fun calculateMinusYPosition(){
            var counter = 1
            if (isFirstMove) counter =2

            var y = currentPosition.yCoordinate -1
            var figure: ClassicChessFigure?

            for (i in 1..counter){
                if (chessboard.checkPositionOnBoard(ClassicFigurePosition(currentPosition.xCoordinate,y))){
                    figure = chessboard.returnFigureByPosition(ClassicFigurePosition(currentPosition.xCoordinate,y))
                    if(figure ==null) {
                        list.add(ClassicFigurePosition(currentPosition.xCoordinate,y))
                    }
                }
                y--
            }
        }
        fun calculateMinusXMinusYPosition(){
            val x = currentPosition.xCoordinate -1
            val y = currentPosition.yCoordinate -1
            val figure: ClassicChessFigure?


            if (chessboard.checkPositionOnBoard(ClassicFigurePosition(x,y))) {
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(x,y))
                val isEnemyAtPosition = figure!=null&&figure.colorFigure != currentPlayer

                if (isEnemyAtPosition) {
                    list.add(ClassicFigurePosition(x, y))
                }
            }
        }
        fun calculateXMinusYPosition(){
            val x = currentPosition.xCoordinate +1
            val y = currentPosition.yCoordinate -1
            val figure: ClassicChessFigure?


            if (chessboard.checkPositionOnBoard(ClassicFigurePosition(x,y))) {
                figure = chessboard.returnFigureByPosition(ClassicFigurePosition(x,y))
                val isEnemyAtPosition = figure!=null&&figure.colorFigure != currentPlayer

                if (isEnemyAtPosition) {
                    list.add(ClassicFigurePosition(x, y))
                }
            }
        }

        if(currentPlayer == ColorPlayer.WHITE){
            calculateYPosition()
            calculateXYPosition()
            calculateMinusXYPosition()
        }
        if (currentPlayer == ColorPlayer.BLACK){
            calculateMinusYPosition()
            calculateMinusXMinusYPosition()
            calculateXMinusYPosition()
        }

        return if (list.isEmpty()) null else list.toSet()

    }

    override fun markMove() {
        isFirstMove = false
    }
}