package org.example

import javax.swing.text.Position

fun printMapChess(mutableMap: MutableMap<ClassicFigurePosition, ClassicChessFigure?>){
    for (i in mutableMap){
        println("${i.key.xCoordinate} ${i.key.yCoordinate} ${i.value?.charAliasOnChessboard} ${i.value?.colorFigure} ${i.key.hashCode()}")
    }
}

fun main() {
    val classicChessGame = ClassicChessGame(ColorPlayer.WHITE)
    printMapChess(classicChessGame.chessboard.returnMapFiguresOnChessboard())
    classicChessGame.playGame()
}



//игра в шахматы
/*
формат вывода/вывода:
    игра запускается
    *случайным образом определиться какая сторона ходит первой
    (1)выводиться сводка игры:
        - чей сейчас ход
        - количество забранных фигур и список их обозначений
    (2)выводится стандартная шахматная доска(по направлению к действующему игроку)
    (3)выводится предложение указать какой фигурой сделать ход
//        (ввести координаты фигуры например: А3)
    (4)выводится предложение указать куда будет совершаться ход
        (ввести координаты перемещения фигуры например: А4)
    при неудачном выводится соответствующие предупреждения и просит повторить какое-либо действие(например: на этих координатах нет фигуры)
    при успешном выполнении хода выводится игровое поле(1), (2) (перспективой для другого игрока/ перевернутое) и повторяются этапы (3) (4)

    * на каждом ходу игра проверяет не завершилась ли она

 */
