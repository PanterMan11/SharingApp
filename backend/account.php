<?php

class account
{
    private int $id;
    private string $displayName;
    private string $password;
    private string $email;
    private array $openChats = [];

    public function getId(): int { return $id; }
    public function getDisplayName(): string { return $displayName; }
    public function getPassword(): string { return $password; }
    public function getEmail(): string { return $email; }
    public function getOpenChats(): array { return $openChats; }

    public function toArray() 
    {
        return 
        [
            "id" => $this->id,
            "displayName" => $this->displayName,
            "password" => $this->password,
            "email" => $this->email,
            "open chats" => $this->openChats
        ];
    }

    private function saveAccount(): int
    {
        $filename = "accounts.json";

        $users = [];
        if (file_exists($filename)) 
        {
            $jsonString = file_get_contents($filename);
            $users = json_decode($jsonString, true);
        }
        else
            return 1;

        if (is_array($users))
        {
            foreach ($users as $user)
            {
                if ($user["id"] == $this->id || $user["email"] == $this->email)
                    return 2;
            }
        }
        
        $users[] = $this->toArray();
        
        $jsonData = json_encode($users, JSON_PRETTY_PRINT);
        file_put_contents($filename, $jsonData);

        return 0;
    }

    private function login(): int
    {
        $filename = "accounts.json";

        $users = [];
        if (file_exists($filename)) 
        {
            $jsonString = file_get_contents($filename);
            $users = json_decode($jsonString, true);
        }
        else
            return 1;

        if (is_array($users))
        {
            foreach ($users as $user)
            {
                if ($user["id"] == $this->id && $user["email"] == $this->email && $user["password"] == $this->password)
                {
                    echo "successful login";
                    return 0;
                }
            }
        }

        return 2;
    }

    //set login 1 to login
    public function __construct(int $id, string $displayName, string $email, string $password, int $login): int
    {
        //i still dont know how this witchcraft works
        if (!preg_match("/^[^\s@]+@[^\s@]+\.[^\s@]+$/", $email)) 
        {
            echo "Please enter a valid email";
            return 1;
        }
        
        if (include "encode" == FALSE)
            return 2;

        $this->id = $id;
        $this->displayName = $displayName;
        $this->email = $email;
        $this->password = encode($password);

        if (login == 1)
        {
            if ($this->saveAccount() != 0)
            {
                echo "failed to create account";
                return 3;
            }
        }
        else
        {
            if ($this->login() != 0)
            {
                echo "failed to login";
                return 4;
            }
        }
        
        echo "created account successfully";
        return 0;
    }
}
?>
