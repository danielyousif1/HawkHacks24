use near_sdk::{env, near_bindgen, AccountId, Promise, collections::UnorderedMap};

#[near_bindgen]
#[derive(Default, BorshDeserialize, BorshSerialize)]
pub struct TransactionContract {
    donor: AccountId,
    donee: String,
    donations: UnorderedMap<AccountId, Balance>,
}

impl TransactionContract {
    #[init]
    pub fn new(donee: String) -> Self {
        Self {
            donor: env::predecessor_account_id(),
            donee,
            donations: UnorderedMap::new(b"donations".to_vec()),
        }
    }

    #[payable]
    pub fn transfer(&mut self, amt: Balance) {
        Promise::new(self.donee.clone()).transfer(amt);
        self.donations.insert(&self.donor, &amt);
    }

    pub fn set_donee(&mut self, donee: String) {
        self.donee = donee;
    }

    pub fn get_donee(&self) -> String {
        self.donee.clone()
    }

    pub fn get_total(&self) -> Balance {
        self.donations.get(&self.donor).unwrap_or(0)
    }
}