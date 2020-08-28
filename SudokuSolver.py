# Steps
# print the entire board (before & after)
# place values down
# validate position
# repeat
# backtrack if needed

board = [
    [7, 8, 0, 4, 0, 0, 1, 2, 0],
    [6, 0, 0, 0, 7, 5, 0, 0, 9],
    [0, 0, 0, 6, 0, 1, 0, 7, 8],
    [0, 0, 7, 0, 4, 0, 2, 6, 0],
    [0, 0, 1, 0, 5, 0, 9, 3, 0],
    [9, 0, 4, 0, 6, 0, 0, 0, 5],
    [0, 7, 0, 3, 0, 0, 0, 1, 2],
    [1, 2, 0, 0, 0, 7, 4, 0, 0],
    [0, 4, 9, 2, 0, 6, 0, 0, 7]]


def solve(board):
    # base case: When we get to the very last spot in the board
    open_spot = findEmpty(board)
    if not open_spot:
        return True  # this means we have placed a value in every spot
    else:
        row, col = open_spot  # returns the pair of coordinates with an empty spot

    # this is going to go row by row
    for i in range(1, 10):
        pos = (row, col)
        if valid(board, i, pos):
            board[row][col] = i
            if solve(board):
                return True
            board[row][col] = 0
    return False


def printBoard(board):
    for i in range(len(board)):
        print()
        for j in range(len(board[0])):
            print(board[i][j], ' ', end='')


def findEmpty(board):
    for i in range(len(board)):
        for j in range(len(board[0])):
            if board[i][j] == 0:
                return (i, j)  # returns an empty position
    return None


def valid(board, val, pos):
    # validate Row
    for i in range(len(board[0])):
        if board[pos[0]][i] == val and pos[1] != i:
            return False

    # validate Col
    for i in range(len(board)):
        if board[i][pos[1]] == val and pos[0] != i:
            return False

    # validate Box (this is a little confusing)
    box_x = pos[1] // 3
    box_y = pos[0] // 3
    for i in range(box_y * 3, box_y*3+3):
        for j in range(box_x * 3, box_x*3+3):
            if board[i][j] == val and (i, j) != pos:
                return False
    return True


print("Board before Sudoku Solver Algorithm...")
printBoard(board)
solve(board)
print("\nBoard after Sudoku Solver Algorithm...")
printBoard(board)
