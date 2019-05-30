from FictionalWorld.People import *

# TESTING PERSON CLASS ---
print("---Testing Person Class---")
# init Person
pam = Person("Pam", 20, 10)
print(pam)
jeff = Person("Jeff", 3)
print(jeff)

# negative value of wealth points
try:
    george = Person("George", 22, -48)
    print(george)
except ValueError as e:
    print(e) # Person must have some positive wealth

pamm = Person("Pam", 20, 39)

kyle = Person("Kyle")
print(kyle)

# Person fight
# Person challenge
try:
    pam.challenge(jeff)  # Person objects cannot challenge people, no method
except AttributeError as e:
    print(e)

print(pam.__eq__(jeff))  # false
print(pam.__eq__(pam))  # true
print("Two fighters with the same name and age (different wealth):")
print(pamm.__eq__(pam))  # true

# TESTING FIGHTER CLASS ---
print("---Testing Fighter Class---")
f = Fighter("F", 59, {"spear": 1, "unarmed combat": 5, "mace": 1, "broadsword": 1}, 20)
print(f)

# testing fighter age < 18
try:
    fred = Fighter("Fred", 17, {}, 2)  # Fighter cannot be younger than 18 years old
    # print(type(fred))
    # print(fred)
except ValueError as e:
    print(e)

try:
    fred.challenge(f)  # fred is not defined
except NameError as e:
    print(e)

# testing fighter wealth < 0
try:
    frank = Fighter("Frank", 18, {"spear": 1, "unarmed combat": 5, "mace": 1, "broadsword": 1}, -1)
except ValueError as e:
    print(e)

# frank.challenge(f) # frank is not defined

# testing fighter invalid skills dict
try:
    # liz = Fighter("Liz", 23, {"spear": 11, "unarmed combat": 5, "mace": 0, "broadsword": 0}, 123)
    # liz = Fighter("Liz", 45, {"spear": 4, "unarmed combat": 5, "mace": 0, "broadsword": 0, "test": 0}, 123)
    liz = Fighter("Liz", 45, {"spear": 4, "unarmed combat": 5, "mace": 0}, 123)
except SkillsError as e:
    print(e)

try:
    liz = Fighter("Liz", 23, {"spear": 11, "unarmed combat": 5, "mace": 0, "broadsword": 0}, 123)
    # liz = Fighter("Liz", 45, {"spear": 4, "unarmed combat": 5, "mace": 0, "broadsword": 0, "test": 0}, 123)
except SkillsError as e:
    print(e)

try:
    liz = Fighter("Liz", 23, {"spear": -11, "unarmed combat": 5, "mace": 0, "broadsword": 0}, 123)
    # liz = Fighter("Liz", 45, {"spear": 4, "unarmed combat": 5, "mace": 0, "broadsword": 0, "test": 0}, 123)
except SkillsError as e:
    print(e)

try:
    liz = Fighter("Liz", 23, ["spear", "unarmed combat", "mace", "broadsword"], 123)
except AttributeError as e:
    print(e)

try:
    liz = Fighter("Liz", 45, {"spear": 4, "unarmed combat": 5, "mace": 0, "broadsword": 0, "test": 0}, 123)
except SkillsError as e:
    print(e)

'''
stacy = Person("Stacy", 34, "hi")  # wealth is an int
print("hi")
'''
bob = Person("Bob", 37, 30)

# testing challenge
print("--Testing Challenges--")
print("-Challenge Test 1-")
# spear, unarmed combat, mace, and broadsword
# fighter challenge fighter
henry = Fighter("Henry", 22, {"spear": 0, "unarmed combat": 3, "mace": 1, "broadsword": 0}, 300)
deb = Fighter("Deb", 34, {"spear": 3, "unarmed combat": 5, "mace": 0, "broadsword": 0}, 600)
# print(henry)
# print(deb)
print(henry.name + "'s current number of fights: " + str(henry.num_fights))
print(deb.name + "'s current number of fights: " + str(deb.num_fights))
henry.challenge(deb, "unarmed combat")
print(henry)
print(deb)
print(henry.name + "'s current number of fights: " + str(henry.num_fights))
print(deb.name + "'s current number of fights: " + str(deb.num_fights))

print("--Testing Invalid Skills--")
try:
    deb.challenge(henry, "aaaa") # invalid skill
except SkillsError as e:
    print(e)

try:
    deb.challenge(henry, " mace")
except SkillsError as e:
    print(e)

print("--Testing Invalid Challenges--")
try:
    henry.challenge(bob, "mace")  # bob is a Person only
except OpponentError as e:
    print(e)

try:
    henry.challenge(henry, "mace")  # cannot fight themselves
except OpponentError as e:
    print(e)

print("-Challenge Test 2-")
nancy = Fighter("Nancy", 59, {"spear": 3, "unarmed combat": 1, "mace": 0, "broadsword": 0}, 10)
deb.challenge(nancy, "spear")
print(deb)
print(nancy)
print(deb.name + "'s current number of fights: " + str(deb.num_fights))
print(nancy.name + "'s current number of fights: " + str(nancy.num_fights))

print("--Testing Invalid Wealth--")
# testing fighting against someone with wealth of 0
sara = Fighter("Sara", 34, {"spear": 0, "unarmed combat": 4, "mace": 0, "broadsword": 0}, 0)

try:
    deb.challenge(sara, "mace")
except ChallengeWealthError as e:
    print(e)

# nancy.challenge(henry, "spear")
# henry.challenge(nancy, "spear")
print("--Testing Equal--")
print(nancy.__eq__(henry))
print(nancy.__eq__(nancy))
nn = nancy
print(nn.__eq__(nancy))

nancy.withdraw(henry, "spear")  # cannot withdraw challenge from a non Warrior

print("---Testing Fighter .challenge and .withdraw (Fighter, Warrior, KnightErrant)---")
f1 = Fighter("F1", 34, {"spear": 3, "unarmed combat": 2, "mace": 0, "broadsword": 1}, 199)
w1 = Warrior("W1", 34, {"spear": 1, "unarmed combat": 2, "mace": 0, "broadsword": 1}, 11)
bob = Warrior("Bob", 81, {"spear": 0, "unarmed combat": 1, "mace": 0, "broadsword": 1}, 164)
print(w1)
f1.challenge(w1, "spear")
f1.withdraw(w1, "spear")  # f1 has not fought anyone yet
print(w1)

deb.challenge(w1, "spear")  # fighter
henry.challenge(w1, "mace")  # fighter
bob.challenge(w1, "unarmed combat")  # Warrior adds to Warrior list
deb.challenge(w1, "broadsword")
print(w1)

# issuing the same challenge to the person
deb.challenge(w1, "spear")
print(w1)  # same as before
# withdrawing a challenge that doesn't exist
deb.withdraw(w1, "mace")

# testing valid withdraw
deb.withdraw(w1, "spear")  # fighter removes from warrior ch list
print(w1)
henry.withdraw(w1, "mace")  # fighter removes from warrior ch list
print(w1)

print("--Testing Warrior.challenge and .withdraw (Warrior, KnightErrant)--")
# bob is warrior, w1 is warrior
bob.withdraw(w1, "unarmed combat")  # warrior removes from warrior ch list, cannot
print(w1)
w2 = Warrior("W2", 34, {"spear": 1, "unarmed combat": 2, "mace": 0, "broadsword": 1}, 276)
w2.challenge(w1, "spear")
print(w1)
print(w2)
w2.challenge(deb, "broadsword") # w2 wins the fight, deb is also an ordinary fighter
print(deb)
print(w2)  # empty
print(w1)
w2.withdraw(w1, "spear")
print(w1)
w2.withdraw(deb, "mace") # cannot withdraw challenge from a fighter that is not a warrior
try:
    w2.challenge(w2, "broadsword")  # warrior fighting themselves is error
except OpponentError as e:
    print(e)

# now adding KnightErrants tests
print("--Testing KnightErrant .challenge and .withdraw--")
ke = KnightErrant("KE", 26, {"spear": 7, "unarmed combat": 8, "mace": 9, "broadsword": 10}, 710)  # not traveling, free
bon = KnightErrant("Bon", 23, {"spear": 7, "unarmed combat": 8, "mace": 9, "broadsword": 10}, 710, True)  # traveling, busy
# test fighter challenge and withdraw to ke
print(f1)
f1.challenge(ke, "spear")
f1.challenge(ke, "spear")
f1.challenge(bon, "mace")
f1.challenge(bon, "unarmed combat")
deb.challenge(ke, "broadsword")
print(ke)
print(bon)
f1.withdraw(ke, "spear")  # not enough fights
deb.withdraw(ke, "broadsword")  # deb withdraw the fight
print(ke)

# test warrior challenge and withdraw to ke
w2.challenge(bon, "spear")
print(bon)
w2.withdraw(bon, "spear")
print(bon)

# testing ke challenge and withdraw to fighters and warrior
# ke challenge and withdraw to fighter
bon.challenge(f1, "mace")  # busy
ke.challenge(f1, "spear")  # default. also the challenge already exists in knight
ke.withdraw(f1, "unarmed combat") # cannot withdraw a challenge from a non warrior
# ke challenge and withdraw to warrior
print(w2)
bon.challenge(w2, "mace")  # busy
ke.challenge(w2, "unarmed combat")
ke.challenge(w2, "spear")
print(w2)
bon.traveling = False
bon.challenge(w2, "broadsword")
bon.traveling = True
bon.withdraw(w2, "broadsword")  # busy
print(w2)

ke.withdraw(w2, "unarmed combat")
print(w2)

# testing ke challenge and withdraw with another ke
ray = KnightErrant("Ray", 26, {"spear": 5, "unarmed combat": 2, "mace": 6, "broadsword": 7}, 301)
ray.challenge(ke, "spear")
ray.challenge(bon, "spear") # cannot keep requesting the same challenge
ray.challenge(bon, "spear")
print(ke)
print(bon)
bon.challenge(ray, "spear") # bon is traveling, cannot
ke.challenge(bon, "broadsword")
print(bon)
ke.withdraw(bon, "broadsword")
print(bon)

# TESTING WARRIOR CLASS ---
print("---Testing Warrior Class (Warrior, KnightErrant)---")
print("--Testing Warrior eq--")
print(w1.__eq__(w2))  # false
x = w1
y = Warrior("W1", 34, {"spear": 0, "unarmed combat": 1, "mace": 0, "broadsword": 2}, 211)
print(w1.__eq__(x))  # true
print(w1.__eq__(y))  # true
print("--Testing KnightErrant eq--")
print(ke.__eq__(bon))  # false

print("--Testing Warrior accept and declines--")
print(w1)
print(f1)
# accept first
w1.accept_first()  # f1 wins, + 25 wealth, levels up once
print(w1)
print(f1)
# accept random

try:
    w1.accept_random()
    print(w1)
except ChallengeWealthError as e:
    print(e)
# decline first
w1.decline_first()
print(w1)
# decline random
print(w2)
w2.decline_random()
print(w2)
print("---Testing KnightErrant accept and declines---")
print(bon)  # busy
bon.accept_first()
bon.accept_random()
bon.decline_first()
bon.decline_random()
ray.challenge(ke, "broadsword")
ray.challenge(ke, "mace")
deb.challenge(ke, "spear") # has enough money
print(w2)
try:
    w2.challenge(ke, "mace")
except ChallengeWealthError as e:
    print(e)
shelby = Warrior("Shelby", 19, {"spear": 0, "unarmed combat": 1, "mace": 2, "broadsword": 5}, 354)  # must have positive wealth
shelby.challenge(ke, "mace")
print(ke)
print()

# accept first
ke.accept_first() # ke wins against f1, default
print(ke)
print("-will test ke accept random-")
# accept random
ke.accept_random()
print(ke)
print("-tested ke accepted random, list of challenges changed-")
# decline first
ke.decline_first()
print(ke)
# decline random
ke.decline_random()
print(ke)

print("---Testing KnightErrant travel and return from travel---")
print(bon)
bon.return_from_travel()
print(bon)
print(ray)
ray.travel("Seahill Harbor")
print(ray)
ray.return_from_travel()
print(ray)

print("--Testing scenarios with certain class type winning a challenge--")
# warrior vs. warrior
fay = Warrior("Fay", 19, {"spear": 3, "unarmed combat": 3, "mace": 2, "broadsword": 5}, 354)  # must have positive wealth
print(shelby) # warrior
print(fay) # warrior
shelby.challenge(fay, "spear")
print(fay)
fay.accept_first()
print(fay)
print(shelby)
print()

# knight vs. knight
k1 = KnightErrant("K1", 19, {"spear": 3, "unarmed combat": 3, "mace": 2, "broadsword": 5}, 354)
k2 = KnightErrant("K2", 23, {"spear": 4, "unarmed combat": 5, "mace": 6, "broadsword": 7}, 600)
print(k1)
print(k2)
k1.challenge(k2, "spear")
print(k2)
k2.accept_first()
print()

# fighter vs. warrior
# fighter wins
fi1 = Fighter("FI1", 19, {"spear": 5, "unarmed combat": 5, "mace": 5, "broadsword": 6}, 200)
wa1 = Warrior("WA1", 32, {"spear": 3, "unarmed combat": 3, "mace": 2, "broadsword": 5}, 300)
print(fi1)
print(wa1)
fi1.challenge(wa1, "spear")
print(wa1)
wa1.accept_first()
# fighter skill + 1
# +25 wealth
print(fi1)
print(wa1)

print()

# fighter vs. knight
# fighter wins
fi2 = Fighter("FI2", 19, {"spear": 9, "unarmed combat": 5, "mace": 5, "broadsword": 6}, 200)
kne1 = KnightErrant("KNE1", 32, {"spear": 3, "unarmed combat": 3, "mace": 2, "broadsword": 5}, 300)
print(fi2)
print(kne1)
fi2.challenge(kne1, "spear")
print(kne1)
kne1.accept_first()
# fighter skill + 2
# +40 wealth
print(fi2)
print(kne1)

print()

# knight vs. warrior
# warrior win
wa2 = Warrior("WA2", 45, {"spear": 2, "unarmed combat": 7, "mace": 5, "broadsword": 8}, 200)
kne2 = KnightErrant("KNE2", 32, {"spear": 3, "unarmed combat": 3, "mace": 2, "broadsword": 5}, 300)
print(wa2)
print(kne2)
wa2.challenge(kne2, "broadsword")
print(kne2)
kne2.accept_first()
# warrior skill + 1
# +20 wealth
print(wa2)
print(kne2)

print()
# knight win, knight vs warrior
kne2.challenge(wa2, "spear")
print(wa2)
print(kne2)
wa2.accept_first()  # default

print()
# testing another case
w1 = Warrior("W1", 32, {"spear": 2, "unarmed combat": 7, "mace": 5, "broadsword": 8}, 400)
w2 = Warrior("W2", 26, {"spear": 2, "unarmed combat": 7, "mace": 5, "broadsword": 8}, 200)
print(w1)
print(w2)
w1.challenge(w2, "mace")
print(w2)
w2.challenge(w1, "mace") # invalid
print(w1)

w2.withdraw(f1, "mace") # cannot withdraw challenge from a fighter that is not a warrior
knightTest = KnightErrant("W1", 32, {"spear": 2, "unarmed combat": 7, "mace": 5, "broadsword": 8}, 222)
print(w1.__eq__(knightTest)) # true
testF = KnightErrant("F1", 34, {"spear": 3, "unarmed combat": 2, "mace": 0, "broadsword": 1}, 642)
print(f1.__eq__(testF))  # true
print()

w1.challenge(f1, "mace")  # default
print()

print(f1)
print(w1)
w1.challenge(f1, "spear")  # f1 wins, +25 wealth points, + 1 skill
print(f1)
print()

w2.challenge(w1, "spear")
f1.challenge(w1, "spear")
print(w1)
print(w2)
w1.accept_first()  # random winner will be chosen
print()

# nk = KnightErrant("Nk", 34, {"spear": 3, "unarmed combat": -2, "mace": 0, "broadsword": 1}, 642)
# print(help(Person))
# print(help(Fight))
# print(help(Fighter))
# print(help(Warrior))
# print(help(KnightErrant))

p = Person("p")
print(p)
ff = Fighter("ff", 18)
print(ff)
ff.wealth = 40
ff.challenge(f1, "spear")

print()
ft = Fighter("ft", 32, {"spear": 2, "unarmed combat": 7, "mace": 5, "broadsword": 8}, 400)
wt = Warrior("wt", 32, {"spear": 2, "unarmed combat": 7, "mace": 5, "broadsword": 8}, 400)
ft.challenge(wt, "spear")
ft.challenge(f1, "spear")
ft.withdraw(wt, "spear")  # valid
ft.challenge(w2, "mace")
ft.withdraw(w2, "mace")