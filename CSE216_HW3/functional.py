from functools import reduce

def flatten(l):
    # to flatten a list where some items in the list could, themselves, be lists. We
    # will call these nested lists.

    # USE RECURSION

    # base case
    if not l:  # if l is empty
        return l

    if isinstance(l[0], list):
        return flatten(l[0]) + flatten(l[1:])
    else:
        return [l[0]] + flatten(l[1:])


def reverse(l):
    # reverse a nested list while still maintaining the nested structures
    if not l:  # if l is empty
        return l

    if isinstance(l[-1], list):
        return [reverse(l[-1])] + reverse(l[:len(l)-1])
    else:
        return [l[-1]] + reverse(l[:len(l)-1])


def compress(l):
    # remove consecutive duplicates from the list
    if len(l) < 2:
        return l

    fst = l[0]
    snd = l[1]
    if fst == snd:
        return compress(l[1:])
    else:
        return [l[0]] + compress(l[1:])


def capitalized(items: list) -> list:
    # items is a list of strings
    return list(filter(lambda x: x[0].isupper(), items))


def longest(strings: list, from_start=True) -> object:
    # from_start is true, then get earlier
    # from_start is false, then get later
    return ((reduce(lambda x, y: x if len(x) >= len(y) else y, strings)) if from_start else (reduce(lambda x, y: x if len(x) > len(y) else y, strings))) if strings else None


def composition(f, g):
    # takes two functions, f and g
    return lambda *args, **kwargs: g(f(*args, **kwargs))


# list of n functions
def n_composition(*functions):
    return (reduce(composition, *functions)) if len(*functions) != 0 else None

