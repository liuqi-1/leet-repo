class Solution:
    def checkOverlap(self, radius: int, xCenter: int, yCenter: int, x1: int, y1: int, x2: int, y2: int) -> bool:
        if xCenter < x1 and x1 - xCenter > radius:
            return False
        if xCenter > x2 and xCenter - x2 > radius:
            return False
        if yCenter < y1 and y1 - yCenter > radius:
            return False
        if yCenter > y2 and yCenter - y2 > radius:
            return False
        if xCenter < x1 and yCenter < y1:
            return (x1 - xCenter) ** 2 + (y1 - yCenter) ** 2 <= radius ** 2
        if xCenter < x1 and yCenter > y2:
            return (x1 - xCenter) ** 2 + (y2 - yCenter) ** 2 <= radius ** 2
        if xCenter > x2 and yCenter < y1:
            return (x2 - xCenter) ** 2 + (y1 - yCenter) ** 2 <= radius ** 2
        if xCenter > x2 and yCenter > y2:
            return (x2 - xCenter) ** 2 + (y2 - yCenter) ** 2 <= radius ** 2
        return True
