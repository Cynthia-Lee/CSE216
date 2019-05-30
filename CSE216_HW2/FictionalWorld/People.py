import random


class Person:
    """
    This is a class for a Person in the Fictional World.

    Attributes:
        name (str): The name of the person.
        age (int): The age of the person.
        wealth (int): The wealth points the person has.
    """

    def __init__(self, name: str, age: int = 17, wealth: int = 0):
        """
        The constructor for Person class.
        The wealth points a person has must be positive.

        Parameters:
            name (str): The name of the person.
            age (int): The age of the person.
            wealth (int): The wealth points the person has.
        """

        self.name = name
        self.age = age
        self.wealth = wealth  # optional wealth: int
        # The default creation of a new person creates a non-adult with zero wealth.
        if age >= 18:
            self.adult = True
        else:
            self.adult = False
        if wealth < 0:
            raise ValueError("Person must have some positive wealth.")

    def __eq__(self, other):
        """
        The function for checking the equality of two people.
        The name-plus-age combination uniquely identifies a person.

        Returns:
            bool: True or False based on equality.
        """

        if isinstance(other, Person) and self.name == other.name and self.age == other.age:
            return True
            # checking two people are equal if they have the same name and age
            # methods to check whether two people are equal
        else:
            return False

    def __str__(self):
        """
        The function to show the Person's information with a String.

        Returns:
            str: String that shows the information of the person with name, age, wealth.
        """

        return "Name: " + self.name + ", Age: " + str(self.age) + ", Wealth: " + str(self.wealth)
        # to print the state of each fighter/person
        # can use this to check if fights actually change the state as required by specifications


class Fight:
    """
    This is a class for a Fight. Two fighters can engage in a fight.

    Attributes:
        fighter1 (Fighter): One of the fighters for the fight.
        fighter2 (Fighter): The other fighter for the fight.
        skill (str): The skill that the fighters will use in the fight.
    """

    # Two attributes for the two fighters in a fight.
    # A method called winner() that returns the winner of the fight.
    def __init__(self, fighter1: 'Fighter', fighter2: 'Fighter', skill: str):
        """
        The constructor for Fight class.

        Parameters:
            fighter1 (Fighter): One of the fighters for the fight.
            fighter2 (Fighter): The other fighter for the fight.
            skill (str): The skill that the fighters will use in the fight.
        """

        self.fighter1 = fighter1
        self.fighter2 = fighter2
        self.skill = skill
        # if isinstance(fighter1, Fighter) and isinstance(fighter2, Fighter):

    def winner(self):
        """
        The function to decide the winner of the fight.
        The winner is the fighter that has the greater skill level of the skill used in the fight.
        If both skill levels are the same, the winner is picked randomly.

        Returns:
            Fighter: The fighter that wins the fight.
        """

        if self.fighter1.skills.get(self.skill) > self.fighter2.skills.get(self.skill):
            # winner is f1
            winner = self.fighter1
        elif self.fighter1.skills.get(self.skill) < self.fighter2.skills.get(self.skill):
            # winner is f2
            winner = self.fighter2
        else:  # if skill level is equal
            # winner is determined by luck
            winner = random.choice([self.fighter1, self.fighter2])
        print(winner.name + " has won the fight!")
        self.fighter1.num_fights = self.fighter1.num_fights + 1
        self.fighter2.num_fights = self.fighter2.num_fights + 1
        return winner


class Fighter(Person):
    """
    This is a class for a Fighter.

    Attributes:
        name (str): The name of the fighter.
        age (int): The age of the fighter. Must be an adult.
        skills (dict): Skills and skill levels of the fighter.
        wealth (int): The wealth points the fighter has.
    """

    def __init__(self, name: str, age: int, skills: dict = {"spear": 0, "unarmed combat": 0, "mace": 0, "broadsword": 0}, wealth: int = 0):
        """
        The constructor for ComplexNumber class.

        Parameters:
            name (str): The name of the fighter.
            age (int): The age of the fighter. Must be an adult.
            skills (dict): Skills and skill levels of the fighter.
            wealth (int): The wealth points the fighter has.
        """

        if age < 18:
            # try-except
            raise ValueError("Fighter cannot be younger than 18 years old.")
        elif wealth < 0:
            raise ValueError("Fighter must have some positive wealth.")
        else:
            # There are four types of skills – spear, unarmed combat, mace, and broadsword.
            if len(skills) == 4 and ("spear" in skills.keys()) and ("unarmed combat" in skills.keys()) and (
                    "mace" in skills.keys()) and ("broadsword" in skills.keys()):
                if (10 >= skills.get("spear") >= 0) and (10 >= skills.get("unarmed combat") >= 0) and (10 >= skills.get("mace") >= 0) and (10 >= skills.get("broadsword") >= 0):
                    valid_dict = True
                else:
                    valid_dict = False
                    raise SkillsError("Skill levels must be 0 to 10. Fighter cannot be instantiated.")
            else:
                valid_dict = False
                raise SkillsError("Invalid skill list. Fighter cannot be instantiated.")

            if valid_dict:
                Person.__init__(self, name, age, wealth)
                self.__skills = skills  # made skills private so that Fighters do not know skill levels of one another
                self.num_fights = 0
        # A Fighter is an adult person who has some positive wealth and a set of skills. Of course, just
        # having a skill doesn’t mean that the fighter has perfected it, so a skill is associated with a level,
        # which is a score between 0 and 10. The skill details of each fighter must be private.

    def __str__(self):
        """
        The function to show the Fighters's information with a String.

        Returns:
            str: String that shows the information of the fighter with name, age, wealth, skills
        """

        return Person.__str__(self) + ", Skills: " + str(self.__skills)

    # getter of private variable
    @property
    def skills(self):
        return self.__skills

    # SKILLS, using a dict
    # skills = {
    #     "spear": 0,
    #     "unarmed combat": 0,
    #     "mace": 0,
    #     "broadsword": 0
    # }

    def fighter_lvl_up(self, f: 'Fighter', skill: str, lvl_amount: int):
        """
        The function that levels up the fighter's skill by an amount.

        Parameters:
            f (Fighter): The fighter.
            skill (int): The skill that will level up.
            lvl_amount (int): The amount that the skill will level up by.
        """

        if f.skills.get(skill) + lvl_amount <= 10:
            f.skills[skill] = f.skills.get(skill) + lvl_amount
            print(f.name + " has leveled up in their skill in " + skill + "!")
            # dict[key] = dict[key], get value there + 1
        else:
            f.skills[skill] = 10
            print(f.name + " has leveled up in their skill in " + skill + "!")

    # challenge method and returns None
    # This method must carry out the responsibility
    # of ensuring that all the rules of a challenge are observed (e.g., wealth change, skill change,
    # whether or not the challenge can even be issued and/or accepted, etc.). [Note that you may
    # have to override this method in some other classes.]
    def challenge(self, recip: 'Fighter', skill: str):
        """
        The function so that a fighter can challenge another fighter.

        Parameters:
            recip (Fighter): The fighter that the challenge will be issued to.
            skill (str): The skill for the fight.

        Returns:
            None
        """

        if not isinstance(recip, Fighter):
            raise OpponentError("A fight can only happen between two Fighters. The opponent must be a Fighter.")
            # only can happen between two Fighters
        if recip.__eq__(self):
            # cannot fight themselves
            raise OpponentError("The Fighter cannot fight themselves.")
        if self.wealth == 0 or recip.wealth == 0:
            raise ChallengeWealthError(
                "A fighter cannot enter into a fight if the challenger or recipient has a wealth of zero.")
            # A fighter also cannot enter into a fight (as a challenger or as the recipient of a challenge) if
            # their wealth is zero.
        if skill not in ["spear", "unarmed combat", "mace", "broadsword"]:
            raise SkillsError(
                "Only one skill may be used in a fight. It must be either: spear, unarmed combat, mace, broadsword")
        else:
            # Only one skill may be used in a fight. If b is able to, s/he will accept the challenge immediately.
            # The outcome of the challenge is a winner, determined by which fighter has a higher level
            # of the chosen skill. If both a and b have the same level, the winner is determined by luck
            # (see random.choice). Otherwise, the fighter with higher skill level wins. The winner gains
            # 10 wealth points, and the loser forfeits 10 wealth points (restricted to a minimum of zero
            # points)
            # Each fight also provides a random chance for each participating fighter to add 1 point to their
            # skill level (for the skill chosen in that fight), subject to the maximum limit of 10.
            if isinstance(recip, Warrior):
                # same person cannot keep issuing challenges
                valid = True
                for ele_list in recip.ch_list:
                    check = (ele_list[0].__eq__(self) and ele_list[1].__eq__(recip)) or (ele_list[0].__eq__(recip) and ele_list[1].__eq__(self))
                    if check and (ele_list[2] == skill):
                        valid = False
                        break
                if valid:
                    recip.ch_list.append([self, recip, skill])
                else:
                    print("The Fighter cannot keep requesting the same challenge.")
            elif isinstance(recip, Fighter):
                # ordinary Fighters, fight happens immediately
                fight = Fight(self, recip, skill)

                # fight takes place
                winner = fight.winner()

                if winner == self:
                    loser = recip
                else:  # if winner is recip:
                    loser = self

                amount = 10
                if (isinstance(winner, Fighter)) and (not isinstance(winner, Warrior)) and (isinstance(loser, Warrior)):
                    # fighter vs knight, fighter wins
                    if isinstance(loser, KnightErrant):
                        # fighter +40 wealth, +2 skill lvl
                        # knight gets no skill, -40 wealth
                        amount = 40
                        self.fighter_lvl_up(winner, skill, 2)
                    # fighter vs warrior, fighter wins
                    elif isinstance(loser, Warrior):
                        # fighter +25 wealth, +1 skill lvl
                        # warrior gets no skill, -25 wealth
                        amount = 25
                        self.fighter_lvl_up(winner, skill, 1)
                elif (isinstance(winner, Warrior)) and (isinstance(loser, KnightErrant)):
                    # warrior vs knight, warrior wins
                    # if isinstance(loser, KnightErrant):
                    # warrior +20 wealth, +1 skill lvl
                    # knight gets no skill, -20 wealth
                    amount = 20
                    self.fighter_lvl_up(winner, skill, 1)
                else:
                    # else = default case, lvl up winner = 60%, looser = 30%, 10 wealth points
                    amount = 10
                    lvl_up_winner_chance = random.randint(1, 100)  # want winner to have 60% to level up
                    lvl_up_loser_chance = random.randint(1, 100)  # want loser to have 30% to level up
                    if lvl_up_winner_chance <= 60:
                        self.fighter_lvl_up(winner, skill, 1)
                    if lvl_up_loser_chance <= 30:
                        self.fighter_lvl_up(loser, skill, 1)

                # distribute wealth
                winner.wealth = winner.wealth + amount
                if loser.wealth >= amount:
                    loser.wealth = loser.wealth - amount
                else:
                    loser.wealth = 0

        return None

    def withdraw(self, person_challenged: 'Fighter', skill: str):
        """
        The function for a fighter to withdraw a challenge.

        Parameters:
            person_challenged (Fighter): The fighter that the challenge was issued to.
            skill (str): The skill for the fight for that challenge.
        """

        # to withdraw a challenge request s/he issued.
        valid = False
        if isinstance(person_challenged, Warrior):
            for ele_list in person_challenged.ch_list:
                if ele_list[0].__eq__(self) and ele_list[2] == skill:
                    if self.num_fights > 0:
                        person_challenged.ch_list.remove(ele_list)
                    valid = True
            '''
            if self.num_fights >= 1:
                for ele_list in person_challenged.ch_list:
                    if ele_list[0].__eq__(self) and ele_list[2] == skill:
                        person_challenged.ch_list.remove(ele_list)
                        valid = True
            '''
            # find fight in fight list and remove it
            if self.num_fights <= 0:
                print(self.name + " cannot withdraw from the challenge because they have not fought anyone yet. (Fighter does not have any injuries)")
            if not valid:
                # if not found, has error
                print("Cannot find challenge. " + self.name + " cannot withdraw from the challenge because the challenge does not exist.")
        else:
            print("Cannot withdraw challenge from a Fighter that is not a Warrior.")
        # However, if a fighter issues a challenge to a warrior, but then ends up
        # fighting someone else before this warrior accepts the request, the fighter has the right to withdraw
        # the request (e.g., the fighter may have suffered an injury in this fight, and cannot fight the warrior
        # any more).


class Warrior(Fighter):
    """
    This is a class for a Warrior.

    Attributes:
        name (str): The name of the fighter.
        age (int): The age of the fighter. Must be an adult.
        skills (dict): Skills and skill levels of the fighter.
        wealth (int): The wealth points the fighter has.
        ch_list (list): The list of challenges that they received and have not been accepted or declined yet.
    """

    # A warrior can keep a list of challenge requests from other fighters, and can accept or decline each request.
    # A warrior may also simply leave requests unanswered.
    def __init__(self, name: str, age: int, skills: dict, wealth: int = 0):
        """
        The constructor for Warrior class.

        Parameters:
            name (str): The name of the fighter.
            age (int): The age of the fighter. Must be an adult.
            skills (dict): Skills and skill levels of the fighter.
            wealth (int): The wealth points the fighter has.
        """

        Fighter.__init__(self, name, age, skills, wealth)
        self.ch_list = []

    # challenge list should be valid, format is a list with dict elements
    # each element will have: challenger, recip, skill
    def challenge(self, recip: 'Fighter', skill: str):
        """
        The function so that a warrior can challenge another fighter.

        Parameters:
            recip (Fighter): The fighter that the challenge will be issued to.
            skill (str): The skill for the fight.

        Returns:
            None
        """

        # If Warrior A challenges Warrior B, and then Warrior B does NOT accept the challenge,
        # but instead challenge Warrior A. Now both Warriors have unanswered challenges from each other.
        # In this case, it'd make sense to prevent B from challenging A and print something that explains why issuing the challenge was not allowed.
        if isinstance(recip, Warrior):
            valid = True
            for ele_list in self.ch_list:
                if ele_list[0].__eq__(recip) and ele_list[2] == skill:
                    valid = False
            if not valid:
                print("Invalid challenge. Challenge already exists in " + self.name + "'s list of challenges.")
            else:
                Fighter.challenge(self, recip, skill)
        else:  # ordinary fighter
            Fighter.challenge(self, recip, skill)
        return None
    # However, if a fighter issues a challenge to a warrior, but then ends up
    # fighting someone else before this warrior accepts the request, the fighter has the right to withdraw
    # the request (e.g., the fighter may have suffered an injury in this fight, and cannot fight the warrior
    # any more).

    def direct_accept_challenge(self, recip: 'Fighter', skill: str):
        """
        The function to execute the challenge when a Warrior accepts a challenge from their list of challenges.

        Parameters:
            recip (Fighter): The fighter that the challenged this Warrior.
            skill (str): The skill for the fight.

        Returns:
            None
        """

        if self.wealth == 0 or recip.wealth == 0:
            raise ChallengeWealthError("A fighter cannot enter into a fight if the challenger or recipient has a wealth of zero.")

        fight = Fight(self, recip, skill)
        # fight takes place
        winner = fight.winner()

        if winner == self:
            loser = recip
        else:  # if winner is recip:
            loser = self

        amount = 10
        if (isinstance(winner, Fighter)) and (not isinstance(winner, Warrior)) and (isinstance(loser, Warrior)):
            # fighter vs knight, fighter wins
            if isinstance(loser, KnightErrant):
                # fighter +40 wealth, +2 skill lvl
                # knight gets no skill, -40 wealth
                amount = 40
                self.fighter_lvl_up(winner, skill, 2)
            # fighter vs warrior, fighter wins
            elif isinstance(loser, Warrior):
                # fighter +25 wealth, +1 skill lvl
                # warrior gets no skill, -25 wealth
                amount = 25
                self.fighter_lvl_up(winner, skill, 1)
        elif (isinstance(winner, Warrior)) and (isinstance(loser, KnightErrant)):
            # warrior vs knight, warrior wins
            # if isinstance(loser, KnightErrant):
            # warrior +20 wealth, +1 skill lvl
            # knight gets no skill, -20 wealth
            amount = 20
            self.fighter_lvl_up(winner, skill, 1)
        else:
            # else = default case, lvl up winner = 60%, looser = 30%, 10 wealth points
            amount = 10
            lvl_up_winner_chance = random.randint(1, 100)  # want winner to have 60% to level up
            lvl_up_loser_chance = random.randint(1, 100)  # want loser to have 30% to level up
            if lvl_up_winner_chance <= 60:
                self.fighter_lvl_up(winner, skill, 1)
            if lvl_up_loser_chance <= 30:
                self.fighter_lvl_up(loser, skill, 1)

        # distribute wealth
        winner.wealth = winner.wealth + amount
        if loser.wealth >= amount:
            loser.wealth = loser.wealth - amount
        else:
            loser.wealth = 0

        return None

    def accept_random(self):
        """
        The function that accepts a random challenge from the warrior's list of challenges.
        """

        if not (len(self.ch_list) == 0):
            # accept random challenges from a list of challenges
            choice = random.choice(self.ch_list)
            if (not isinstance(choice[0], KnightErrant)) or ((isinstance(choice[0], KnightErrant) and (not choice[0].traveling))):
                # choice[challenger, recipient, skill]
                # challenge(self, recipient, skill)
                # self will challenge challenger choice[0]
                self.direct_accept_challenge(choice[0], choice[2])
                self.ch_list.remove(choice)

    def decline_random(self):
        """
        The function that declines a random challenge from the warrior's list of challenges.
        """

        if not (len(self.ch_list) == 0):
            choice = random.choice(self.ch_list)
            self.ch_list.remove(choice)

    def accept_first(self):
        """
        The function that accepts the first challenge from the warrior's list of challenges.
        """

        if not (len(self.ch_list) == 0):
            choice = self.ch_list[0]
            if (not isinstance(choice[0], KnightErrant)) or ((isinstance(choice[0], KnightErrant) and (not choice[0].traveling))):
                # choice[challenger, recipient, skill]
                # challenge(self, recipient, skill)
                # self will challenge challenger choice[0]
                self.direct_accept_challenge(choice[0], choice[2])
                self.ch_list.remove(choice)

    def decline_first(self):
        """
        The function that declines the first challenge from the warrior's list of challenges.
        """

        if not (len(self.ch_list) == 0):
            choice = self.ch_list[0]
            self.ch_list.remove(choice)

    def __str__(self):
        """
        The function to show the Warrior's information with a String.

        Returns:
            str: String that shows the information of the fighter with name, age, wealth, skills, list of challenges
        """

        s = Fighter.__str__(self) + ", List of challenges: "  # + str(self.ch_list)
        for ele_list in self.ch_list:
            s = s + ("[Challenger: " + ele_list[0].name + ", Recipient: " + ele_list[1].name + ", Skill: " + ele_list[2] + "] ")
        return s


class KnightErrant(Warrior):
    """
    This is a class for a Knight Errant.

    Attributes:
        name (str): The name of the fighter.
        age (int): The age of the fighter. Must be an adult.
        skills (dict): Skills and skill levels of the fighter.
        wealth (int): The wealth points the fighter has.
        ch_list (list): The list of challenges that they received and have not been accepted or declined yet.
        traveling (bool): Boolean for if the knight errant is traveling or not.
    """

    # For instance, they travel a lot.
    # Therefore, at any given point of time, a knight errant may be traveling or not.
    def __init__(self, name: str, age: int, skills: dict, wealth: int = 0, traveling: bool = False):
        """
        The constructor for KnightErrant class.

        Parameters:
            name (str): The name of the fighter.
            age (int): The age of the fighter. Must be an adult.
            skills (dict): Skills and skill levels of the fighter.
            wealth (int): The wealth points the fighter has.
            traveling (bool): Boolean for if the knight errant is traveling or not.
        """

        Warrior.__init__(self, name, age, skills, wealth)
        self.traveling = traveling

    # When a knight-errant is traveling, he cannot challenge another fighter. Nor can he accept a
    # challenge. Others can, however, leave challenge requests during this time.
    def challenge(self, recip: 'Fighter', skill: str):
        """
        The function so that a knight errant can challenge another fighter.

        Parameters:
            recip (Fighter): The fighter that the challenge will be issued to.
            skill (str): The skill for the fight.

        Returns:
            None
        """

        if not self.traveling:
            Warrior.challenge(self, recip, skill)
        else:
            print("When a knight-errant is traveling, they cannot challenge another fighter.")
            return None

    def accept_random(self):
        """
        The function that accepts a random challenge from the knight errant's list of challenges.
        """

        if not self.traveling:
            Warrior.accept_random(self)
        else:
            print(self.name + " cannot accept a random request because they are traveling.")

    def decline_random(self):
        """
        The function that declines a random challenge from the knight errant's list of challenges.
        """

        if not self.traveling:
            Warrior.decline_random(self)
        else:
            print(self.name + " cannot decline a random request because they are traveling.")

    def accept_first(self):
        """
        The function that accepts the first challenge from the knight errant's list of challenges.
        """

        if not self.traveling:
            Warrior.accept_first(self)
        else:
            print(self.name + " cannot accept the first request because they are traveling.")

    def decline_first(self):
        """
        The function that declines the first challenge from the knight errant's list of challenges.
        """

        if not self.traveling:
            Warrior.decline_first(self)
        else:
            print(self.name + " cannot decline the first request because they are traveling.")

    # A knight-errant can sometimes find treasures when they are traveling. This treasure’s worth
    # (in terms of amount of wealth) gets added to the knight-errant’s wealth when they return
    # from a travel.

    def travel(self, dest: str):
        """
        The function to mark the beginning of a journey for the knight errant.

        Parameters:
            dest (str): The destination the knight errant is traveling to.
        """
        self.traveling = True
        print(self.name + " embarked on a travel to " + dest + ".")

    def return_from_travel(self):
        """
        The function to mark the to mark the return from a journey for the knight errant.
        With treasure related activities.
        """

        treasure_wealth = 0
        treasure_chance = random.randint(1, 100)  # 50% chance for finding treasure
        if treasure_chance <= 50:
            treasure_wealth = random.randint(1, 200)
            print(self.name + " has found treasure worth " + str(treasure_wealth) + " in wealth points!")
        else:
            print(self.name + " has not found treasure from this travel.")
        self.wealth = self.wealth + treasure_wealth
        self.traveling = False

    def __str__(self):
        """
        The function to show the Knight Errant's information with a String.

        Returns:
            str: String that shows the information of the fighter with name, age, wealth, skills, list of challenges, traveling
        """

        return Warrior.__str__(self) + ", Traveling?: " + str(self.traveling)


class OpponentError(Exception):
    """
    This is a class for the OpponentError exception.
    Raised when opponent is invalid.
    """

    pass


class ChallengeWealthError(Exception):
    """
    This is a class for the ChallengeWealthError exception.
    Raised when either or both fighter's wealth are 0 when being challenged.
    """

    pass


class SkillsError(Exception):
    """
    This is a class for the SkillsError exception.
    Raised when fighter's skill list is invalid.
    Skill list is invalid when skills are >10 or <0, skill keys is not these: "spear","unarmed combat","mace","broadsword"
    """

    pass
