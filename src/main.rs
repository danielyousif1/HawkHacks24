use near_sdk::json_types::{U128, U64};
use near_sdk::{env, near, AccountId, Promise, NearToken};

fn main() {}

#[near(serializers = [json])]
pub struct TransactionContract {
    pub donor: AccountId,
    pub donee: String,
}

#[near]
impl TransactionContract {
    #[payable]
    pub fn transfer(&mut self, amt: U128) {
        let donor = env::predecessor_account_id();
        let mut prev_donations:NearToken = self.donations.get(&donor).unwrap_or(NearToken::from_near(0));
        Promise::new(self.donee.clone()).transfer(amt);
        self.donations.insert(&donor, &prev_donations.saturating_add(amt));
    }

    pub fn change_donee(&mut self, don: String) {
        self.donee = don;
    }

    pub fn get_donee(&self) -> String {
        &self.donee;
    }

    pub fn get_total(&self) -> U128 {
        self.donations.get(&self.donor).unwrap_or(NearToken::from_near(0))
    }
}