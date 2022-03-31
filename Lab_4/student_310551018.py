import time

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys

def q1(driver):
    print("Question 1:")
    driver.maximize_window()
    driver.get('https://www.nycu.edu.tw')

    element = driver.find_element(By.CSS_SELECTOR, '#menu-1-9942884 a[title=消息]')
    element.click()

    news1 = driver.find_element(By.XPATH, "//div[@id='eael-advance-tabs-7dbbb0c']/div[2]/div[1]/ul/li[1]/a")
    news1.click()

    news1_title = driver.find_element(By.XPATH, "//div[@id='content']/article/header/h1")
    print(news1_title.text)

    news1_content = driver.find_element(By.XPATH, "//div[@id='content']/article/div[1]/p[1]")
    print(news1_content.text)

def q2(driver):
    print("\nQuestion 2:")
    driver.switch_to.new_window('tab')
    driver.get('https://www.google.com')
    search_input = driver.find_element(By.XPATH, "//input[@aria-label='搜尋']")
    search_input.click()
    search_input.send_keys("310551018")
    search_input.send_keys(Keys.ENTER)
    result2 = driver.find_element(By.XPATH, "(//a/h3)[2]")
    print(result2.text)

def main():
    driver = webdriver.Edge()
    q1(driver)
    q2(driver)
    driver.close()
    driver.quit()

if __name__ == "__main__":
    main()