import csv
import random
from faker import Faker

fake = Faker('es_ES')

DEPARTMENTS = [
    'Engineering', 'Marketing', 'Sales',
    'Human Resources', 'Finance', 'Operations'
]

OUTPUT_FILE = 'src/main/resources/employees.csv'
TOTAL_RECORDS = 100_000

def generate_employees():
    with open(OUTPUT_FILE, mode='w', newline='', encoding='utf-8') as file:
        writer = csv.writer(file)
        writer.writerow(['first_name', 'last_name', 'email', 'department', 'salary'])

        for _ in range(TOTAL_RECORDS):
            writer.writerow([
                fake.first_name(),
                fake.last_name(),
                fake.email(),
                random.choice(DEPARTMENTS),
                round(random.uniform(18000, 80000), 2)
            ])

    print(f'{TOTAL_RECORDS} empleados generados en {OUTPUT_FILE}')

if __name__ == '__main__':
    generate_employees()