from viewer_creator import ViewerCreator
from multiple_accumulate import MultipleAccumulate

text = "test.txt"
image = "python.jpg"


if __name__ == '__main__':
    """
    ==================
            z1
    ==================
    """

    creator = ViewerCreator()

    tv = creator.create_viewer(text)
    # print(tv.get_data())
    tv.view()

    iv = creator.create_viewer(image)
    iv.view()

    """
    ==================
            z2
    ==================
    """
    def my_sum(x, y): return x + y

    def division(x, y): return x / y

    ma = MultipleAccumulate([4, 2, 1], my_sum, lambda x, y: x * y, division, lambda x, y: x - y, lambda x, _: x)

    # print(ma.get_data())

    """
    ===========================
            Duck typing
    ===========================
    """
    # function below assumes that something is a text viewer if it has the method "get_data()"
    def detect_text_viewer(test_object):
        try:
            test_object.get_data()
            # print(f"{test_object.get_data()}")
        except AttributeError as e:
            # print("=================================================================")
            # print(f"-----\t{e}\t-----")
            # print("=================================================================")
            print(f"{type(test_object).__name__} is NOT a text viewer\n\n")
        else:
            print(f"{type(test_object).__name__} is a text viewer\n\n")

    print("\n")
    detect_text_viewer(tv)
    detect_text_viewer(iv)
    detect_text_viewer(ma)
