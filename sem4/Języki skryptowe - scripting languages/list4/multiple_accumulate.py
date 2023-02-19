from functools import reduce


class MultipleAccumulate:
    def __init__(self, data_list, *accumulate_functions):
        self.data_list = data_list
        '''
        # if I wanted to throw an exception when encountering "not function" I could use this part of a code
        for function in accumulate_functions:
            if not hasattr(function, "__call__"):
                raise ValueError(f"Invalid argument \'{function}\'")
        '''
        self.accumulate_functions = accumulate_functions

    def get_data(self):
        """
        if there is an argument that is not a function it just gets ignored when computing data
        """
        def name(fun, i):
            if fun.__name__ == "<lambda>":
                return f"#{i}_lambda"
            else:
                return fun.__name__

        to_return = {}

        for num, function in enumerate(self.accumulate_functions):
            '''
            hasattr checks whether something is a function (has an attribute __call__)
            for python 3.x but before 3.2, for all the other versions use callable(function)
            '''
            if hasattr(function, '__call__'):
                to_return[name(function, num + 1)] = reduce(function, self.data_list)

        return to_return
